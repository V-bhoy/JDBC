package com.util;

import com.entity.User;

public class Layouts {

    public static void printBold(String message) {
        System.out.println("\n*********************************************\n");
        System.err.println(message);
        System.out.println("\n**********************************************\n");
    }

    public static void printMargin() {
        System.out.println("\n-----------------------------------------------------------------\n");
    }
    
    public static void printUserData(User user) {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
        System.out.println("USER ID: " + user.getUserId());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("GENDER: " + user.getGender());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("CONTACT: " + user.getContact());
        System.out.println("CITY: " + user.getCity());
        System.out.println("STATE: " + user.getState());
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------\n");
    }
}
