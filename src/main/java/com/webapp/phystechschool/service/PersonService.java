package com.webapp.phystechschool.service;

import com.webapp.phystechschool.constants.FormConstants;
import com.webapp.phystechschool.model.Person;
import com.webapp.phystechschool.model.Roles;
import com.webapp.phystechschool.repository.PersonRepository;
import com.webapp.phystechschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean savePersonDetails(Person person) {
        boolean isSaved = false;
        Roles roles = rolesRepository.getByRoleName(FormConstants.ROLE_STUDENT);
        person.setRoles(roles);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (person.getPersonId() > 0)
            isSaved = true;
        return isSaved;
    }
}
