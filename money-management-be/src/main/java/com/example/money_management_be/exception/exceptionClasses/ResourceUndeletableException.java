package com.example.money_management_be.exception.exceptionClasses;

import com.example.money_management_be.exception.exceptionClasses.base.ServiceException;
import org.springframework.http.HttpStatus;

public class ResourceUndeletableException extends ServiceException {

    public ResourceUndeletableException(Class undeletableClass, Class relatedClass, String... searchParamsMap) {
        super("exception.resource.undeletable", HttpStatus.CONFLICT, undeletableClass, relatedClass, searchParamsMap);
    }

    public ResourceUndeletableException() {
        super("exception.resource.default.undeletable", HttpStatus.CONFLICT);
    }

}
