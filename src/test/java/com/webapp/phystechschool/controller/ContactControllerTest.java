package com.webapp.phystechschool.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.webapp.phystechschool.model.Contact;
import com.webapp.phystechschool.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ContactControllerTest {

    private ContactService contactService;
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        contactService = mock(ContactService.class);
        contactController = new ContactController(contactService);
    }

    @Test
    void testSaveMessage() {
        Contact contact = new Contact();
        Errors errors = mock(Errors.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        String result = contactController.saveMessage(contact, errors, redirectAttributes);

        verify(contactService, times(1)).saveMessageDetails(contact);
        assertEquals("redirect:/contact", result);
    }
}
