package com.webapp.phystechschool.controller;

import com.webapp.phystechschool.model.Person;
import com.webapp.phystechschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String showDashboardPage(Model model, Authentication authentication, HttpSession httpSession) {
        Person person = personRepository.findByEmail(authentication.getName());

        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());

        if (person.getPhystechClass() != null && person.getPhystechClass().getName() != null)
            model.addAttribute("studentClass", person.getPhystechClass().getName());

        httpSession.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
