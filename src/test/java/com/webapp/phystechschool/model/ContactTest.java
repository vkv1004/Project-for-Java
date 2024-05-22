package com.webapp.phystechschool.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {
    @Test
    public void testContactCreation() {
        Contact contact = new Contact();
        contact.setContactId(1);
        contact.setStatus("New");
        contact.setName("John Doe");
        contact.setEmail("johndoe@example.com");
        contact.setSubject("Test Subject");
        contact.setMessage("This is a test message.");

        assertEquals(1, contact.getContactId());
        assertEquals("New", contact.getStatus());
        assertEquals("John Doe", contact.getName());
        assertEquals("johndoe@example.com", contact.getEmail());
        assertEquals("Test Subject", contact.getSubject());
        assertEquals("This is a test message.", contact.getMessage());
    }

    @Test
    public void testContactValidation() {
        Contact contact = new Contact();
        contact.setName("A");
        contact.setEmail("invalidemail");
        contact.setSubject("Test");
        contact.setMessage("Short");
    }
}
