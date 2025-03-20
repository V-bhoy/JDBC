package com.util;

public class ContactValidator {
   private static final String CONTACT_REGEX = "^[0-9]{10}$";
    
    public static boolean isValidContact(String contact) {
        return contact != null && contact.matches(CONTACT_REGEX);
    }
}
