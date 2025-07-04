//package com.example.ubermoto.config;
//
//import com.example.ubermoto.model.AppUser;
//import com.example.ubermoto.repository.AppUserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@RequiredArgsConstructor
//public class DataLoader {
//
//    private final AppUserRepository userRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public ApplicationRunner initializer() {
//        return args -> {
//            if (userRepo.count() == 0) {
//                AppUser admin = new AppUser();
//                admin.setUsername("admin");
//                admin.setPassword(passwordEncoder.encode("admin123"));
//                admin.setRoles("ROLE_ADMIN,ROLE_USER");
//                userRepo.save(admin);
//                System.out.println("Usuário admin criado: admin / admin123");
//            }
//        };
//    }
//}