package com.example.ubermoto.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI uberMotoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UberMoto API")
                        .version("v1")
                        .description("API de geolocalização para solicitação de motos por proximidade"));
    }
}