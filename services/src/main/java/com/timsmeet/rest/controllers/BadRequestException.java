package com.timsmeet.rest.controllers;

import com.timsmeet.errors.ErrorSpecification;

public class BadRequestException extends ReportableException {

    private static final long serialVersionUID = -418889871648699560L;

    public BadRequestException(ErrorSpecification errorSpecification) {
        super(errorSpecification);
    }

    public BadRequestException(ErrorSpecification errorSpecification, Throwable cause) {
        super(errorSpecification, cause);
    }

}
