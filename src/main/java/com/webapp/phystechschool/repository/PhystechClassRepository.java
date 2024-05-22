package com.webapp.phystechschool.repository;

import com.webapp.phystechschool.model.PhystechClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhystechClassRepository extends JpaRepository<PhystechClass, Integer> {
}
