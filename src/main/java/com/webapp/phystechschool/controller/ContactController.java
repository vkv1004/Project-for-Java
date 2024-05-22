package com.webapp.phystechschool.controller;

import com.webapp.phystechschool.model.Contact;
import com.webapp.phystechschool.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class ContactController {
    private final ContactService contactService;

    @RequestMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(value = "/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            log.error("Проверка контактной формы не удалась из-за: " + errors);
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        redirectAttributes.addFlashAttribute("successMessage","Ваш запрос был успешно отправлен. Мы свяжемся с вами в ближайшее время.");
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model, @PathVariable(name = "pageNum") int pageNum,
                                        @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);

        List<Contact> contactMsgs = msgPage.getContent();

        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @RequestMapping("/closeMsg")
    public String closeMsg(@RequestParam int id, Authentication authentication, RedirectAttributes redirectAttributes) {
        contactService.updateMsgStatus(id, authentication.getName());
        redirectAttributes.addFlashAttribute("successMessage","Тикет был успешно закрыт");
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }
}