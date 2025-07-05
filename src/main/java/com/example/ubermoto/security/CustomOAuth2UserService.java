//package com.example.ubermoto.security;
//
//import com.example.ubermoto.model.AppUser;
//import com.example.ubermoto.model.Role;
//import com.example.ubermoto.repository.AppUserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CustomOAuth2UserService
//        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    private final AppUserRepository userRepo;
//    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//
//    public CustomOAuth2UserService(AppUserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest)
//            throws OAuth2AuthenticationException {
//
//        OAuth2User oauth2User = delegate.loadUser(userRequest);
//        String googleId = oauth2User.getAttribute("sub");
//        String email    = oauth2User.getAttribute("email");
//        String name     = oauth2User.getAttribute("name");
//
//        // busca por email (já cadastrado via form-login) ou cria novo
//        AppUser user = userRepo.findByEmail(email)
//                .orElseGet(() -> {
//                    AppUser novo = new AppUser();
//                    novo.setGoogleId(googleId);
//                    novo.setEmail(email);
//                    novo.setName(name);
//                    novo.setRole(Role.USER);        // garante ROLE_USER
//                    return userRepo.save(novo);
//                });
//
//        // atualiza sempre googleId, name e role caso estejam faltando
//        user.setGoogleId(googleId);
//        user.setName(name);
//        if (user.getRole() == null) {
//            user.setRole(Role.USER);
//        }
//        userRepo.save(user);
//
//        List<GrantedAuthority> authorities = List.of(
//                new SimpleGrantedAuthority("ROLE_" + user.getRole())
//        );
//
//        return new DefaultOAuth2User(
//                authorities,
//                oauth2User.getAttributes(),
//                "sub"
//        );
//    }
//}