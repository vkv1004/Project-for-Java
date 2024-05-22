package com.webapp.phystechschool.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionHandlerTest {

    @Test
    void testShowExceptionPage() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Exception exception = new Exception("Test Exception");

        ModelAndView errorPage = globalExceptionHandler.showExceptionPage(exception);

        assertEquals("error", errorPage.getViewName());
        assertEquals("Test Exception", errorPage.getModel().get("errormsg"));
    }
}
