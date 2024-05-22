package com.webapp.phystechschool.service;

import com.webapp.phystechschool.constants.FormConstants;
import com.webapp.phystechschool.model.Contact;
import com.webapp.phystechschool.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    public void testSaveMessageDetails() {
        Contact contact = new Contact();
        contact.setStatus(null);

        when(contactRepository.save(contact)).thenReturn(contact);

        contactService.saveMessageDetails(contact);

        assertEquals(FormConstants.OPEN, contact.getStatus());
        verify(contactRepository, times(1)).save(contact);
    }
}
