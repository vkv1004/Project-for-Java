package com.webapp.phystechschool.controller;

import com.webapp.phystechschool.model.Address;
import com.webapp.phystechschool.model.Person;
import com.webapp.phystechschool.model.Profile;
import com.webapp.phystechschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller("ProfileControllerBean")
public class ProfileController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfilePage(HttpSession httpSession) {
        Person person = (Person) httpSession.getAttribute("loggedInPerson");

        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());

        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            profile.setMobileNumber(person.getAddress().getMobileNumber());
            profile.setAddress(person.getAddress().getAddress());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping(value = "/updateProfile")
    public String updateUserProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                    HttpSession httpSession, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors())
            return "profile.html";

        Person person = (Person) httpSession.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());

        if (person.getAddress() == null || !(person.getAddress().getAddressId() > 0)) {
            person.setAddress(new Address());
        }
        person.getAddress().setMobileNumber(profile.getMobileNumber());
        person.getAddress().setAddress(profile.getAddress());
        person.getAddress().setState(profile.getState());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setZipCode(profile.getZipCode());

        Person savedPerson = personRepository.save(person);
        httpSession.setAttribute("loggedInPerson", savedPerson);

        redirectAttributes.addFlashAttribute("successMessage", "Ваш профиль был успешно обновлен");
        return "redirect:/displayProfile";
    }
}
