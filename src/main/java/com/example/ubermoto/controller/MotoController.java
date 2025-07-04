package com.example.ubermoto.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotoController {

    @GetMapping("/public/status")
    public String status() {
        return "UberMoto está no ar!";
    }

    // Só quem tiver ROLE_ADMIN poderá chamar esse endpoint
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secure/admin")
    public String adminArea() {
        return "Bem-vindo à área restrita, admin!";
    }
}