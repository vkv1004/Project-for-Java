package com.webapp.phystechschool.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HomeControllerTest {
    @Test
    void testShowHomePage() {
        HomeController homeController = new HomeController();

        String viewName = homeController.showHomePage();

        assertEquals("index.html", viewName);
    }
}
