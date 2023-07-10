package com.example.demo.exception;

public class WrongIdException extends Exception {
    /**
     * 
     * @param class_name Provide class name for proper message
     */
    public WrongIdException(String class_name) {
        super("ID not found for class " + class_name);
    }
}
