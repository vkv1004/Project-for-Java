package com.webapp.phystechschool.repository;

import com.webapp.phystechschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1, c.updatedAt = ?2, c.updatedBy = ?3 WHERE c.contactId = ?4")
    int updateStatusById(String status, LocalDateTime now, String updatedBy, int id);
}
