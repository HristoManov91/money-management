package com.example.money_management_be.exception.exceptionClasses;

import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class ValidationException extends Exception {

    private Errors errors;

    public ValidationException(Errors errors) {
        this.errors = errors;
    }
}
