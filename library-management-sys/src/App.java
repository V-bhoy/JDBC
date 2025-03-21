import java.util.Scanner;

import com.controller.BookController;
import com.controller.UserController;
import com.util.Layouts;

public class App {

    public static void displayMainSection() {
        Layouts.printMargin();
        System.out.println("Make a choice between 1 to 4: ");
        Layouts.printMargin();
        System.out.println("1. Users Section");
        System.out.println("2. Books Section");
        System.out.println("3. Borrowers Section");
        System.out.println("4. Exit");
        Layouts.printMargin();
    }

    public static void displayUsersApp() {
        Layouts.printMargin();
        System.out.println("Make a choice between 1 to 8: ");
        Layouts.printMargin();
        System.out.println("1. Register User");
        System.out.println("2. Update User");
        System.out.println("3. Delete User");
        System.out.println("4. Fetch User By Id");
        System.out.println("5. Fetch User By Email");
        System.out.println("6. Fetch User By Contact");
        System.out.println("7. Fetch All Users");
        System.out.println("8. Exit");
        Layouts.printMargin();
    }

    public static void handleUsersApp(int choice) {
        UserController uc = new UserController();
        switch (choice) {
        case 1:
            uc.registerUser();
            break;
        case 2:
            uc.updateUser();
            break;
        case 3:
            uc.deleteUser();
            break;
        case 4:
            uc.getUser(4);
            break;
        case 5:
            uc.getUser(5);
            break;
        case 6:
            uc.getUser(6);
            break;
        case 7:
            uc.getAllUsers();
            break;
        default:
            System.out.println("\nEnter a valid choice!\n");
        }

    }

    public static void displayBooksApp() {
        Layouts.printMargin();
        System.out.println("Make a choice between 1 to 8: ");
        Layouts.printMargin();
        System.out.println("1. Register Books");
        System.out.println("2. Update Book");
        System.out.println("3. Delete Book");
        System.out.println("4. Fetch Book By Barcode");
        System.out.println("5. Fetch Book By Author");
        System.out.println("6. Fetch Book By Title");
        System.out.println("7. Fetch All Books");
        System.out.println("8. Exit");
        Layouts.printMargin();
    }

    public static void handleBooksApp(int choice) {
        BookController bc = new BookController();
        switch (choice) {
        case 1:
            bc.registerBooks();
            break;
        case 2:
            bc.updateBook();
            break;
        case 3:
            bc.deleteBook();
            break;
        case 4:
            bc.getBook(4);
            break;
        case 5:
            bc.getBook(5);
            break;
        case 6:
            bc.getBook(6);
            break;
        case 7:
            bc.getAllBooks();
            break;
        default:
            System.out.println("\nEnter a valid choice!\n");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        Layouts.printBold("WELCOME TO THE LIBRARY MANAGEMENT SYSTEM");
        boolean active = true;
        while (active) {
            displayMainSection();
            int choice = sc.nextInt();
            switch (choice) {
            case 1:
                boolean flag = true;
                while (flag) {
                    displayUsersApp();
                    int ch = sc.nextInt();
                    if (ch == 8) {
                        flag = false;
                        continue;
                    }
                    handleUsersApp(ch);
                }
                break;
            case 2:
                boolean bflag = true;
                while (bflag) {
                    displayBooksApp();
                    int ch = sc.nextInt();
                    if (ch == 8) {
                        bflag = false;
                        continue;
                    }
                    handleBooksApp(ch);
                }
                break;
            case 3:
                break;
            case 4:
                active = false;
                Layouts.printBold("THANK YOU, VISIT AGAIN!!");
                break;
            default:
                System.out.println("\nEnter a valid choice!\n");

            }
        }

        sc.close();
    }
}
