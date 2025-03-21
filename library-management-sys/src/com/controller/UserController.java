package com.controller;

import java.util.List;
import java.util.Scanner;

import com.dao.UserDAOImpl;
import com.entity.User;
import com.enums.Gender;
import com.service.UserService;
import com.service.UserServiceImpl;
import com.util.ContactValidator;
import com.util.EmailValidator;
import com.util.Layouts;

public class UserController {
    private UserService us = new UserServiceImpl(new UserDAOImpl());
    Scanner sc = new Scanner(System.in);

    public void registerUser() {
        User user = new User();

        Layouts.printMargin();

        System.out.println("Enter your FIRST NAME: ");
        String firstName = sc.nextLine().trim();
        if (firstName.isEmpty()) {
            System.out.println("First name cannot be empty!");
            return;
        }

        System.out.println("\nEnter your LAST NAME: ");
        String lastName = sc.nextLine().trim();
        if (lastName.isEmpty()) {
            System.out.println("Last name cannot be empty!");
            return;
        }

        System.out.println("\nEnter your EMAIL: ");
        String email = sc.nextLine().trim();
        if (email.isEmpty()) {
            System.out.println("Email cannot be empty!");
            return;
        }
        if (!EmailValidator.isValidEmail(email)) {
            System.out.println("Enter valid email!");
            return;
        }

        System.out.println("\nEnter your CONTACT: ");
        String contact = sc.nextLine().trim();
        if (contact.isEmpty() || !ContactValidator.isValidContact(contact)) {
            System.out.println("Invalid contact number!");
            return;
        }

        System.out.println("\nEnter your GENDER: M/F");
        String genderInput = sc.nextLine().trim().toUpperCase();
        Gender gender = null;
        if (genderInput.equals("M") || genderInput.equals("F")) {
            gender = Gender.valueOf(genderInput);
        } else {
            System.out.println("Invalid gender! Please enter 'M' or 'F'.");
            return;
        }

        System.out.println("\nEnter your CITY: ");
        String city = sc.nextLine().trim();
        if (city.isEmpty()) {
            System.out.println("City cannot be empty!");
            return;
        }

        System.out.println("\nEnter your STATE: ");
        String state = sc.nextLine().trim();
        if (state.isEmpty()) {
            System.out.println("State cannot be empty!");
            return;
        }

        Layouts.printMargin();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setContact(contact);
        user.setGender(gender);
        user.setCity(city);
        user.setState(state);

        String message = us.addUser(user);
        Layouts.printBold(message);
    }

    public void updateUser() {
        User user = new User();

        Layouts.printMargin();
        System.out.println("Please enter the user id to update:");

        if (sc.hasNextInt()) {
            int userId = sc.nextInt();
            if (userId <= 0) {
                System.out.println("\nEnter a valid user Id greater than 0!\n");
                return;
            }
            sc.nextLine();
            user.setUserId(userId);
        } else {
            System.out.println("\nInvalid Input! Please enter a numeric userId!\n");
            return;
        }

        System.out.println("\nEnter the values to be updated!");

        System.out.println("\nEnter your new FIRST NAME or press ENTER to skip:");
        String firstName = sc.nextLine().trim();
        if (!firstName.isEmpty()) {
            user.setFirstName(firstName);
        }

        System.out.println("\nEnter your new LAST NAME or press ENTER to skip:");
        String lastName = sc.nextLine().trim();
        if (!lastName.isEmpty()) {
            user.setLastName(lastName);
        }

        System.out.println("\nEnter your new EMAIL or press ENTER to skip:");
        String email = sc.nextLine().trim();
        if (!email.isEmpty()) {
            if (EmailValidator.isValidEmail(email)) {
                user.setFirstName(firstName);
            } else {
                System.out.println("\nPlease enter valid email!\n");
                return;
            }
        }

        System.out.println("\nEnter your new CONTACT or press ENTER to skip:");
        String contact = sc.nextLine().trim();
        if (!contact.isEmpty()) {
            if (ContactValidator.isValidContact(contact)) {
                user.setContact(contact);
            } else {
                System.out.println("\nPlease enter valid contact!\n");
                return;
            }
        }

        System.out.println("\nEnter your new GENDER (M/F) or press ENTER to skip:");
        String gender = sc.nextLine().trim().toUpperCase();
        if (!gender.isEmpty()) {
            if (gender.equals("M") || gender.equals("F")) {
                user.setGender(Gender.valueOf(gender));
            } else {
                System.out.println("\nPlease choose valid M/F as your gender!\n");
                return;
            }
        }

        System.out.println("\nEnter your new CITY or press ENTER to skip:");
        String city = sc.nextLine().trim();
        if (!city.isEmpty()) {
            user.setCity(city);
        }

        System.out.println("\nEnter your new STATE or press ENTER to skip:");
        String state = sc.nextLine().trim();
        if (!state.isEmpty()) {
            user.setState(state);
        }

        String message = us.updateUser(user);
        Layouts.printBold(message);
    }
    
    public void deleteUser() {
        Layouts.printMargin();
        System.out.println("Please enter the user id to delete:");
        if (sc.hasNextInt()) {
            int userId = sc.nextInt();
            if (userId <= 0) {
                System.out.println("\nEnter a valid user Id greater than 0!\n");
                return;
            }
            String message = us.deleteUser(userId);
            Layouts.printBold(message);
        } else {
            System.out.println("\nInvalid Input! Please enter a numeric userId!\n");
            return;
        }
    }

    public void getUser(int choice) {
        Layouts.printMargin();
        User user = null;
        if (choice == 4) {
            System.out.println("Please enter the user Id:");
            if (sc.hasNextInt()) {
                int userId = sc.nextInt();
                if (userId <= 0) {
                    System.out.println("\nEnter a valid user Id greater than 0!\n");
                    return;
                }
                user = us.getUser(userId);
            } else {
                System.out.println("\nInvalid Input! Please enter a numeric userId!\n");
                return;
            }
        } else if (choice == 5) {
            System.out.println("Please enter the user email:");
            String email = sc.nextLine().trim();
            if (!email.isEmpty() && EmailValidator.isValidEmail(email)) {
                user = us.getUserByEmail(email);
            } else if (email.isEmpty()) {
                System.out.println("\nEmail cannot be empty!\n");
                return;
            } else {
                System.out.println("\nEnter valid email!\n");
                return;
            }
        } else {
            System.out.println("Please enter the user contact:");
            String contact = sc.nextLine().trim();
            if (!contact.isEmpty() && ContactValidator.isValidContact(contact)) {
                user = us.getUserByContact(contact);
            } else if (contact.isEmpty()) {
                System.out.println("\nContact cannot be empty!\n");
                return;
            } else {
                System.out.println("\nEnter valid contact!\n");
                return;
            }
        }
        if (user == null) {
            Layouts.printBold("Found no details for the user!");
        } else {
            Layouts.printUserData(user);
        }
    }
    
    public void getAllUsers() {
        List<User> users = us.getAllUsers();
        if (users.isEmpty()) {
            Layouts.printBold("Empty users list!");
        }
        else {
            for (User user : users) {
                Layouts.printUserData(user);
            }  
        }
    }
}
