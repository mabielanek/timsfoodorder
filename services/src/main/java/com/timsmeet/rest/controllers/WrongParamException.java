package com.timsmeet.rest.controllers;

import com.timsmeet.errors.ErrorSpecification;

public class WrongParamException extends BadRequestException {

    private static final long serialVersionUID = 1671148642985744538L;

    public WrongParamException(ErrorSpecification errorSpecification) {
        super(errorSpecification);
    }

    public WrongParamException(ErrorSpecification errorSpecification, Throwable cause) {
        super(errorSpecification, cause);
    }

}
