//package com.example.ubermoto.controller;
//
//import com.example.ubermoto.service.AppUserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class DebugController {
//
//    private final AppUserService userSvc;
//    private final PasswordEncoder encoder;
//
//    @GetMapping("/debug-match")
//    public String debug() {
//        var user = userSvc.loadUserByUsername("admin");
//        boolean ok = encoder.matches("admin123", user.getPassword());
//        return ok
//                ? "MATCH OK"
//                : "NO MATCH — dbHash=" + user.getPassword();
//    }
//}