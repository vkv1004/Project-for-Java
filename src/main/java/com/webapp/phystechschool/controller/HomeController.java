package com.webapp.phystechschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"", "/", "index"})
    public String showHomePage() {
        return "index.html";
    }

}
