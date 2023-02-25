package com.example.money_management_be.exception.exceptionClasses;

import com.example.money_management_be.exception.exceptionClasses.base.ServiceException;
import org.springframework.http.HttpStatus;

public class ResourceInvalidParameterException extends ServiceException {

    public ResourceInvalidParameterException(Class clazz, String... searchParams) {
        super("exception.resource.invalid.parameter", HttpStatus.BAD_REQUEST, clazz, searchParams);
    }

    public ResourceInvalidParameterException(String searchParams) {
        super("exception.resource.invalid.parameter.generic", HttpStatus.BAD_REQUEST, searchParams);
    }

    public ResourceInvalidParameterException(String errMsg, String... params ) {
        super(errMsg, HttpStatus.BAD_REQUEST, params);
    }
}
