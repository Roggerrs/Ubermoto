package com.example.ubermoto.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.Base64URL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    // ... seus beans já existentes ...

    // 🧠 Este método gera uma chave RSA e transforma ela em um JWK (formato JSON)
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws Exception {
        // 🔧 Gera chave RSA de 2048 bits (padrão para tokens seguros)
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();

        // 🔐 Monta o JWK com a chave pública + privada
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString()) // 🔒 Identificador único da chave
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey); // agrupa a chave
        return new ImmutableJWKSet<>(jwkSet); // envia para o encoder
    }

    // 🏷️ Esse bean usa a chave JWK para assinar os tokens JWT
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    // 🕐 Define a validade do token como 30 segundos
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            if ("access_token".equals(context.getTokenType().getValue())) {
                context.getClaims().claim("exp",
                        Instant.now().plus(30, ChronoUnit.SECONDS)); // ⏱️ 30 segundos
            }
        };
    }
}