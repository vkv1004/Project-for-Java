package com.webapp.phystechschool.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

public class LoginControllerTest {
    @Test
    void testDisplayLoginPage() {
        LoginController loginController = new LoginController();
        Model model = mock(Model.class);

        String viewName = loginController.displayLoginPage(null, null, null, null, model);

        assertEquals("login.html", viewName);
    }
}
