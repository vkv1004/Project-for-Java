package com.webapp.phystechschool.controller;

import com.webapp.phystechschool.model.Person;
import com.webapp.phystechschool.model.PhystechClass;
import com.webapp.phystechschool.repository.PersonRepository;
import com.webapp.phystechschool.repository.PhystechClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    PhystechClassRepository phystechClassRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<PhystechClass> phystechClassList = phystechClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");

        if (!model.containsAttribute("phystechClass")) {
            modelAndView.addObject("phystechClass", new PhystechClass());
        }
        modelAndView.addObject("phystechClasses", phystechClassList);
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public String saveClassDetails(@Valid @ModelAttribute("phystechClass") PhystechClass phystechClass, BindingResult errors,
                                   RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.phystechClass", errors);
            redirectAttributes.addFlashAttribute("phystechClass", phystechClass);
            return "redirect:/admin/displayClasses";
        }
        phystechClassRepository.save(phystechClass);
        redirectAttributes.addFlashAttribute("addMessage", phystechClass.getName() + " был успешно добавлен.");
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int id, RedirectAttributes redirectAttributes) {
        Optional<PhystechClass> phystechClass = phystechClassRepository.findById(id);
        for (Person person : phystechClass.get().getPersons()) {
            person.setPhystechClass(null);
            personRepository.save(person);
        }
        phystechClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        redirectAttributes.addFlashAttribute("addMessage", phystechClass.get().getName() + " был успешно удален");
        return modelAndView;
    }


    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(@RequestParam int classId, HttpSession httpSession,
                                        @RequestParam(value = "error", required = false) String error) {
        Object errorMessage = httpSession.getAttribute("errorMessage");

        Optional<PhystechClass> phystechClass = phystechClassRepository.findById(classId);

        ModelAndView modelAndView = new ModelAndView("students.html");
        modelAndView.addObject("phystechClass", phystechClass.get());
        modelAndView.addObject("person", new Person());

        httpSession.setAttribute("phystechClass", phystechClass.get());

        if (error != null) {
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addNewStudent(@ModelAttribute("person") Person person, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        PhystechClass phystechClass = (PhystechClass) httpSession.getAttribute("phystechClass");

        Person personEntity = personRepository.findByEmail(person.getEmail());

        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + phystechClass.getClassId()
                    + "&error=true");
            httpSession.setAttribute("errorMessage", "Введен неверный адрес электронной почты!");
            return modelAndView;
        } else if (personEntity.getPhystechClass() != null) {
            if (personEntity.getPhystechClass().getClassId() == phystechClass.getClassId()) {
                httpSession.setAttribute("errorMessage", "Ученик уже присутствует в классе");
            } else {
                httpSession.setAttribute("errorMessage", personEntity.getEmail() + " уже является частью " + personEntity.getPhystechClass().getName());
            }
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + phystechClass.getClassId()
                    + "&error=true");
            return modelAndView;
        }

        personEntity.setPhystechClass(phystechClass);
        personRepository.save(personEntity);
        phystechClass.getPersons().add(personEntity);
        phystechClassRepository.save(phystechClass);
        redirectAttributes.addFlashAttribute("successMessage", personEntity.getName() + " был успешно добавлен в " + phystechClass.getName());
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + phystechClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam int personId, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        PhystechClass phystechClass = (PhystechClass) httpSession.getAttribute("phystechClass");

        Optional<Person> person = personRepository.findById(personId);
        person.get().setPhystechClass(null);
        phystechClass.getPersons().remove(person.get());
        PhystechClass phystechClassSaved = phystechClassRepository.save(phystechClass);

        httpSession.setAttribute("phystechClass", phystechClassSaved);
        redirectAttributes.addFlashAttribute("successMessage", person.get().getName() + " был успешно удален из " + phystechClass.getName());
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + phystechClass.getClassId());
        return modelAndView;
    }

}
