package com.timsmeet.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Lists;
import com.timsmeet.dto.ErrorCollection;
import com.timsmeet.dto.ErrorInfo;
import com.timsmeet.services.NotFoundException;
import com.timsmeet.services.WrongOperationException;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody ErrorCollection handleNotFound(HttpServletRequest req, NotFoundException ex) {
        return build(req, ex);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(WrongParamException.class)
    @ResponseBody ErrorCollection handleWrongParam(HttpServletRequest req, NotFoundException ex) {
        return build(req, ex);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(WrongOperationException.class)
    @ResponseBody ErrorCollection handleWrongOperation(HttpServletRequest req, NotFoundException ex) {
        return build(req, ex);
    }

    private static ErrorCollection build(HttpServletRequest req, ReportableException ex) {
        ErrorCollection errors = new ErrorCollection();
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(ex.getErrorCode());
        errorInfo.setMessage(ex.getMessage());
        errorInfo.setRequestUrl(req.getRequestURL().toString());
        errorInfo.setHttpMethod(req.getMethod());
        errors.setErrors(Lists.newArrayList(errorInfo));
        return errors;
    }
}
