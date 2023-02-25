package com.example.money_management_be.exception.exceptionClasses;


import com.example.money_management_be.exception.exceptionClasses.base.ServiceException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ServiceException {

    public ResourceNotFoundException(Class clazz, String... searchParams) {
        super("exception.resource.not.found", HttpStatus.NOT_FOUND, clazz, searchParams);
    }

    public ResourceNotFoundException(String... searchParams) {
        super("exception.resource.not.found.generic", HttpStatus.NOT_FOUND, searchParams);
    }

}
