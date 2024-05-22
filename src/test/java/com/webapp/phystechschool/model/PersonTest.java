package com.webapp.phystechschool.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @Test
    public void testPersonCreation() {
        Person person = new Person();
        person.setPersonId(1);
        person.setName("Alice");
        person.setEmail("alice@example.com");
        person.setPwd("Password123");
        person.setResetPasswordToken("resetToken");
        person.setConfirmpwd("Password123");

        assertEquals(1, person.getPersonId());
        assertEquals("Alice", person.getName());
        assertEquals("alice@example.com", person.getEmail());
        assertEquals("Password123", person.getPwd());
        assertEquals("resetToken", person.getResetPasswordToken());
        assertEquals("Password123", person.getConfirmpwd());
    }

    @Test
    public void testPasswordMatching() {
        Person person = new Person();
        person.setPwd("Password123");
        person.setConfirmpwd("Password123");
    }

    @Test
    public void testEmailValidation() {
        Person person = new Person();
        person.setEmail("invalidemail");
    }
}
