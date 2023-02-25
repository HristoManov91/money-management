package com.example.money_management_be.exception.exceptionClasses.base;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class ServiceException extends BaseException {

    private HttpStatus status;

    public ServiceException(String message, HttpStatus status, Object... args) {
        super(message, args);
        this.status = status;
    }

    public ServiceException(String message, HttpStatus status, String... searchParams) {
        super(message, new Object[]{
            toMap(String.class, String.class, searchParams)
        });
        this.status = status;
    }

    public ServiceException(String message, HttpStatus status, Class clazz, String... searchParams) {
        super(message, new Object[]{
            StringUtils.uncapitalize(clazz.getSimpleName()),
            toMap(String.class, String.class, searchParams)
        });
        this.status = status;
    }

    public ServiceException(String message, HttpStatus status, Class undeletableClass, Class relatedClass, String... searchParamsMap) {
        super(message, new Object[]{
            StringUtils.uncapitalize(undeletableClass.getSimpleName()),
            toMap(String.class, String.class, searchParamsMap),
            StringUtils.uncapitalize(relatedClass.getSimpleName())
        });
        this.status = status;
    }

    public HttpStatus httpStatus() {
        return status;
    }

    public int status() {
        return status.value();
    }
}
