package com.example.money_management_be.validation;

import com.example.money_management_be.repository.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !repository.existsByEmail(value);
    }
}
