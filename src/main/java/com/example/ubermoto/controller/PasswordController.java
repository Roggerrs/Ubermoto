package com.example.ubermoto.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/senha")
public class PasswordController {

    @GetMapping("/gerar")
    public String gerarSenha(@RequestParam String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
}