package com.webapp.phystechschool.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhystechClassTest {
    @Test
    public void testPhystechClassCreation() {
        PhystechClass phystechClass = new PhystechClass();
        phystechClass.setClassId(1);
        phystechClass.setName("Physics");

        Set<Person> persons = new HashSet<>();
        phystechClass.setPersons(persons);

        assertEquals(1, phystechClass.getClassId());
        assertEquals("Physics", phystechClass.getName());
        assertEquals(persons, phystechClass.getPersons());
    }
}
