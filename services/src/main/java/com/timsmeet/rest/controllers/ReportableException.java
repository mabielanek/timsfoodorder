package com.timsmeet.rest.controllers;

import com.timsmeet.errors.ErrorSpecification;

public class ReportableException extends RuntimeException {

    private static final long serialVersionUID = 7553187575206372607L;

    private ErrorSpecification errorSpecification;
    
    public ReportableException(ErrorSpecification errorSpecification) {
        super(errorSpecification.getErrorMessage());
        this.errorSpecification = errorSpecification;
    }
    
    public ReportableException(ErrorSpecification errorSpecification, Throwable cause) {
        super(errorSpecification.getErrorMessage(), cause);
        this.errorSpecification = errorSpecification;
    }
    
    public String getErrorCode() {
        return errorSpecification.getErrorCode();
    }
    
}
