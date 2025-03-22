# Library Management System (JDBC)

## Overview
The **Library Management System** is a Java-based application that helps manage books, book borrowing, returns, and users using **JDBC** with a **MySQL database**. The system includes functionalities such as borrowing books, returning books, checking borrowed books, and managing book availability.

## Features
- Borrow a book using a **barcode** (ISBN) and user ID.
- Return a borrowed book and update the database.
- Renew a borrowed book.
- Check if a book is currently borrowed by a user.
- Retrieve a list of books borrowed by a specific user.
- Track users and books.

## Technologies Used
- **Java** (JDK 17)
- **JDBC** for database connectivity
- **MySQL** for data storage
- **Callable Statements** for stored procedures

## Database Schema
### Tables
#### `books`
| Column             | Type          | Description                         |
|------------------  |-------------- |-------------------------------------|
| `book_id`          | INT (PK)      | Unique book identifier              |
| `title`            | VARCHAR(255)  | Book title                          |
| `author`           | VARCHAR(255)  | Book author                         |
| `isbn`             | VARCHAR(50)   | Unique barcode for books            |
| `available_copies` | INT           | Number of copies available          |

#### `users`
| Column      | Type         | Description                          |
|-------------|------------- |--------------------------------------|
| `user_id`   | INT (PK)     | Unique user identifier               |
| `first_name`| VARCHAR(255) | User's first name                    |
| `last_name` | VARCHAR(255) | User's last name                     |
| `email`     | VARCHAR(255) | User's email                         |
| `contact`   | VARCHAR(255) | Contact number                       |
| `city`      | VARCHAR(255) | City                                 |
| `state`     | VARCHAR(255) | State                                |

#### `borrowed_books`
| Column             | Type          | Description                               |
|-------------------|--------------|-------------------------------------------|
| `borrow_id`      | INT (PK)      | Unique borrow identifier                  |
| `user_id`        | INT (FK)      | Reference to users table                   |
| `book_id`        | INT (FK)      | Reference to books table                   |
| `borrow_date`    | TIMESTAMP     | Timestamp when the book was borrowed       |
| `return_date`    | TIMESTAMP     | Expected return date (14 days after borrow) |
| `actual_return_date` | TIMESTAMP | Date when the book was actually returned   |

## Stored Procedures
### Borrow Book
```sql
CREATE DEFINER=`root`@`localhost` PROCEDURE `library`.`borrow_book`(IN barcode varchar(50), IN user_id int, OUT status boolean, OUT borrow_id int)
BEGIN
	DECLARE bookId int;
    DECLARE copies int;
   
    SELECT book_id, available_copies INTO bookId, copies FROM books WHERE isbn = barcode;
    
     -- Debug: Print values
    SELECT 'DEBUG: Book Check', bookId, copies;
    
    IF bookId is not null and copies > 0 THEN 
    
       INSERT INTO borrowed_books (book_id, user_id) VALUES (bookId, user_id);
       
       UPDATE books SET available_copies = copies - 1 WHERE book_id = bookId;
       
       SET status = true;
       
       SET borrow_id = LAST_INSERT_ID();
    ELSE
       SET status = false;
    END IF;     
END
```
### Return book
```sql
CREATE DEFINER=`root`@`localhost` PROCEDURE `library`.`return_book`(IN borrowed_id int, OUT status boolean)
BEGIN
	DECLARE bookId int;
    DECLARE actual_return TIMESTAMP;
    SELECT book_id, actual_return_date INTO bookId, actual_return FROM borrowed_books WHERE borrow_id = borrowed_id;
    IF bookId IS NOT NULL AND actual_return IS NULL THEN
        -- Update return date
        UPDATE borrowed_books SET actual_return_date = NOW() WHERE borrow_id = borrowed_id;
        
        -- Increment book stock
        UPDATE books SET available_copies = available_copies + 1 WHERE book_id = bookId;
        
        SET status = TRUE;
    ELSE
        SET status = FALSE;
    END IF;
END
```
### Renew book
```sql
CREATE DEFINER=`root`@`localhost` PROCEDURE `library`.`renew_book`(IN borrowed_id int, IN user_id int, OUT status boolean)
BEGIN
	DECLARE due_date TIMESTAMP;
    DECLARE returned_date TIMESTAMP;
    SELECT return_date, actual_return_date INTO due_date, returned_date FROM borrowed_books WHERE borrow_id = borrowed_id AND user_id = user_id;
    IF due_date > NOW() AND returned_date IS NULL THEN
        -- Extend return date by 7 days
        UPDATE borrowed_books SET return_date = DATE_ADD(return_date, INTERVAL 7 DAY) 
        WHERE borrow_id = borrowed_id AND user_id = user_id;
        
        SET status = TRUE;
    ELSE
        SET status = FALSE;
    END IF;
END
```

## Setup and Installation
1. **Clone the repository**
   ```sh
   git clone https://github.com/V-bhoy/JDBC.git
   cd JDBC
   ```
2. **Configure Database Connection**
   - Update `DBConnection.java` with correct database credentials.
3. **Run SQL Schema**
   - Import schema from database.txt into MySQL.
   - Insert dummy data from data.txt
4. **Compile and Run the Project**
   - Add sql connector jar - version 8
   - compile and run

## Author
- **Vaishali Bhoyar** - [GitHub](https://github.com/V-bhoy)

