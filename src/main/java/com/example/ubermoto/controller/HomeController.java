package com.example.ubermoto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        // Coloca o nome do usuário logado no template
        model.addAttribute("username", auth.getName());
        return "index";   // Thymeleaf irá renderizar templates/index.html
    }
}