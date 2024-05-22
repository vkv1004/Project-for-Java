package com.webapp.phystechschool.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {
    @Test
    public void testAddressCreation() {
        Address address = new Address();
        address.setAddressId(1);
        address.setAddress("123 Main St");
        address.setMobileNumber("+7 (123) 456-78-90");
        address.setCity("Moscow");
        address.setState("Russia");
        address.setZipCode("123456");

        assertEquals(1, address.getAddressId());
        assertEquals("123 Main St", address.getAddress());
        assertEquals("+7 (123) 456-78-90", address.getMobileNumber());
        assertEquals("Moscow", address.getCity());
        assertEquals("Russia", address.getState());
        assertEquals("123456", address.getZipCode());
    }
}
