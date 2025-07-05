//package com.example.ubermoto.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import java.io.IOException;
//
//@Component
//public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication)
//            throws IOException, ServletException {
//        // redireciona conforme role
//        var roles = authentication.getAuthorities();
//        if(roles.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//            response.sendRedirect("/admin/dashboard");
//        } else {
//            response.sendRedirect("/user/home");
//        }
//    }
//}