package com.webapp.phystechschool.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileTest {
    @Test
    public void testProfileCreation() {
        Profile profile = new Profile();
        profile.setName("John Doe");
        profile.setEmail("johndoe@example.com");
        profile.setMobileNumber("+7 (123) 456-78-90");
        profile.setAddress("123 Main Street");
        profile.setCity("City");
        profile.setState("State");
        profile.setZipCode("123456");

        assertEquals("John Doe", profile.getName());
        assertEquals("johndoe@example.com", profile.getEmail());
        assertEquals("+7 (123) 456-78-90", profile.getMobileNumber());
        assertEquals("123 Main Street", profile.getAddress());
        assertEquals("City", profile.getCity());
        assertEquals("State", profile.getState());
        assertEquals("123456", profile.getZipCode());
    }

    @Test
    public void testMobileNumberFormat() {
        Profile profile = new Profile();
        profile.setMobileNumber("invalidnumber");
    }

    @Test
    public void testZipCodeValidation() {
        Profile profile = new Profile();
        profile.setZipCode("invalidzipcode");
    }
}
