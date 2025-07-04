package com.example.ubermoto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {
    public static void main(String[] args) {
        String raw = "admin123";
        String hash = new BCryptPasswordEncoder().encode(raw);
        System.out.println(hash);
    }
}