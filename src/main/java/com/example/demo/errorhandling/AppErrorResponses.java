package com.example.demo.errorhandling;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.AgeNotAllowedException;
import com.example.demo.exception.WrongIdException;

@RestControllerAdvice
public class AppErrorResponses {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errores = new HashMap<>();
        Throwable causaRaiz = ex.getRootCause();
        if (causaRaiz instanceof DateTimeParseException) {
            DateTimeParseException formatoInvalidoExcepcion = (DateTimeParseException) causaRaiz;
            errores.put(formatoInvalidoExcepcion.getParsedString(), "Formato de fecha inválido.");
        }

        // En caso de que no sea una excepción de formato de fecha incorrecto, puedes
        // agregar otro manejo o devolver una respuesta genérica de error.

        return errores;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongIdException.class)
    public Map<String, String> handleWrongIdException(WrongIdException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Wrong Id", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AgeNotAllowedException.class)
    public Map<String, String> handleAgeNotAllowedException(AgeNotAllowedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Age", ex.getMessage());
        return errors;
    }
}
