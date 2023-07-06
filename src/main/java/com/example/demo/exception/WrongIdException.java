package com.example.demo.exception;

public class WrongIdException extends Exception {
    /**
     * 
     * @param obj Class name
     */
    public WrongIdException(String class_name) {
        super("ID not found for class " + class_name);
    }
}
