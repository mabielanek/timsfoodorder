package com.timsmeet.services;

import com.timsmeet.errors.ErrorSpecification;
import com.timsmeet.rest.controllers.BadRequestException;

public class NotFoundException extends BadRequestException {

	private static final long serialVersionUID = -800424749757810801L;

    public NotFoundException(ErrorSpecification errorSpecification) {
        super(errorSpecification);
    }

    public NotFoundException(ErrorSpecification errorSpecification, Throwable cause) {
        super(errorSpecification, cause);
    }
	
}
