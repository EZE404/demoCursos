package com.example.demo.exception;

public class AgeNotAllowedException extends Exception {
    /**
     * Se requiere edad mayor a 18 años
     */
    public AgeNotAllowedException() {
        super("El estudiante debe ser mayor de edad.");
    }
}
