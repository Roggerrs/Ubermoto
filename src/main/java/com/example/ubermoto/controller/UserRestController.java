//package com.example.ubermoto.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/user")
//public class UserRestController {
//
//    /**
//     * GET  /user/home
//     * Apenas ROLE_USER ou ROLE_ADMIN podem acessar.
//     * Retorna JSON com mensagem e nome do usuário autenticado.
//     */
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    @GetMapping("/home")
//    public Map<String, String> home(Authentication auth) {
//        return Map.of(
//                "message",  "Bem-vindo à área de usuário!",
//                "username", auth.getName()
//        );
//    }
//}