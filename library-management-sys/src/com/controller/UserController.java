package com.controller;

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
    UserService us = new UserServiceImpl(new UserDAOImpl());
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
}
