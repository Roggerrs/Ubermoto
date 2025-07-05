package com.example.ubermoto.controller;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping; import org.springframework.security.core.Authentication;
@Controller public class HomeController {

    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        Object principal = auth.getPrincipal();

        if (principal instanceof org.springframework.security.oauth2.core.user.DefaultOAuth2User oauthUser) {
            String email = oauthUser.getAttribute("email");
            String nome = oauthUser.getAttribute("name"); // Pode ser nulo dependendo das permissões

            model.addAttribute("username", nome != null ? nome : email);
        } else {
            model.addAttribute("username", auth.getName()); // login tradicional
        }

        return "index"; // index.html com Thymeleaf
    }
}