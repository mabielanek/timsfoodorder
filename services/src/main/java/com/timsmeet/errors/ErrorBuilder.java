package com.timsmeet.errors;


public final class ErrorBuilder {

    private ErrorBuilder() {}
    
    public static ErrorSpecification build(final ErrorDescribedEnum errorDescription, final Object ... params) {
        return new ErrorSpecification() {
            
            @Override
            public String getErrorMessage() {
                return String.format(errorDescription.getErrorMessage(), params);
            }
            
            @Override
            public String getErrorCode() {
                return errorDescription.getErrorCode();
            }
        };
    }

}
