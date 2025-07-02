package com.example.ubermoto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotoController {

    @GetMapping("/public/status")
    public String status() {
        return "UberMoto está no ar!";
    }

    @GetMapping("/secure/admin")
    public String adminArea() {
        return "Bem-vindo à área restrita, admin!";
    }
}

// 01 07 2025