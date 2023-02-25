package com.example.money_management_be.exception.exceptionClasses;

import com.example.money_management_be.exception.exceptionClasses.base.ServiceException;
import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistException extends ServiceException {

    public ResourceAlreadyExistException(Class clazz, String... searchParams) {
        super("exception.resource.already.exists", HttpStatus.CONFLICT, clazz, searchParams);
    }

    public ResourceAlreadyExistException(String... searchParams) {
        super("exception.resource.already.exists.generic", HttpStatus.CONFLICT, searchParams);
    }
}
