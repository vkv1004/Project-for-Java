package com.webapp.phystechschool.controller;

import com.webapp.phystechschool.model.Person;
import com.webapp.phystechschool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/register")
    public String doRegister(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken))
            return "redirect:/dashboard";

        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping(value = "/doregister")
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if (errors.hasErrors()) {
            return "register.html";
        }
        boolean isSaved = personService.savePersonDetails(person);
        if (isSaved) {
            return "redirect:/login?register=true";
        } else {
            return "register.html";
        }
    }
}
