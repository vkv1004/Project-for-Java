package com.webapp.phystechschool.service;

import com.webapp.phystechschool.constants.FormConstants;
import com.webapp.phystechschool.model.Contact;
import com.webapp.phystechschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public void saveMessageDetails(Contact contact) {
        contact.setStatus(FormConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return contactRepository.findByStatus(
                FormConstants.OPEN, pageable);
    }

    public void updateMsgStatus(int id, String updatedBy) {
        int updatedContactRow = contactRepository.updateStatusById(FormConstants.CLOSE, LocalDateTime.now(), updatedBy, id);
    }
}