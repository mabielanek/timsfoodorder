package com.timsmeet.services;

import com.timsmeet.errors.ErrorSpecification;
import com.timsmeet.rest.controllers.BadRequestException;

public class WrongOperationException extends BadRequestException {

    private static final long serialVersionUID = 8594710316261504278L;

    public WrongOperationException(ErrorSpecification errorSpecification) {
        super(errorSpecification);
    }

    public WrongOperationException(ErrorSpecification errorSpecification, Throwable cause) {
        super(errorSpecification, cause);
    }

}
