package com.webapp.phystechschool.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView showExceptionPage(Exception exception) {
        String errorMsg;
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        if (exception.getMessage() != null) {
            errorMsg = exception.getMessage();
        } else if (exception.getCause() != null) {
            errorMsg = exception.getCause().toString();
        } else {
            errorMsg = exception.toString();
        }
        errorPage.addObject("errormsg", errorMsg);
        return errorPage;
    }
}
