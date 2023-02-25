package com.example.money_management_be.exception.exceptionClasses;

import com.example.money_management_be.exception.exceptionClasses.base.BaseException;
import lombok.Getter;

@Getter
public class IncompatibleRequestIdException extends BaseException {

    public IncompatibleRequestIdException(Long dtoId, Long requestId) {
        super("exception.incompatibleRequestId", new Object[]{dtoId, requestId});
    }
}
