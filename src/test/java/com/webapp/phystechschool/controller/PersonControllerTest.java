package com.webapp.phystechschool.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.webapp.phystechschool.model.Person;
import com.webapp.phystechschool.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

public class PersonControllerTest {

    @Test
    void testDoRegister() {
        PersonController personController = new PersonController();
        Model model = mock(Model.class);

        String viewName = personController.doRegister(model);

        assertEquals("register.html", viewName);
    }

    @Test
    void testCreateUser() {
        PersonController personController = new PersonController();
        PersonService personService = mock(PersonService.class);
        personController.personService = personService;

        Person person = new Person();
        person.setName("Test User");

        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(false);
        when(personService.savePersonDetails(person)).thenReturn(true);

        String viewName = personController.createUser(person, errors);

        verify(personService, times(1)).savePersonDetails(person);
        assertEquals("redirect:/login?register=true", viewName);
    }
}
