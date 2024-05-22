package com.webapp.phystechschool.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   @RequestParam(value = "register", required = false) String register,
                                   @RequestParam(value = "reset", required = false) String reset,
                                   Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken))
            return "redirect:/dashboard";

        String errorMessage = null;
        String logoutMessage = null;

        if (error != null) {
            errorMessage = "Указан неверный адрес электронной почты или пароль";
        }
        if (logout != null) {
            logoutMessage = "Вы успешно вышли из системы!";
        }
        if (register != null) {
            logoutMessage = "Вы успешно зарегистрировались. Войдите в систему, используя свои учетные данные!";
        }
        if (reset != null) {
            logoutMessage = "Вы успешно сбросили свой пароль. Войдите в систему, используя свои новые учетные данные!";
        }

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("logoutMessage", logoutMessage);

        return "login.html";
    }

    @GetMapping(value = "logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout=true";
    }
}
