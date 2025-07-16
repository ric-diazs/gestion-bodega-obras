package com.bodega_obra.cl.monitoreo_sistema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(
            new Info()
            .title("API de microservicio Monitoreo de Sistema")
            .version("1.0.0")
            .description("Documentación OAS de la API del microservicio Monitoreo de sistema")
            .contact(
                new Contact()
                .name("Grupo 04 - Sistema de Gestión de Bodega en Obras de Construcción")
                .email("gestion-bodegas@mail.com")
            )
        );
    }
}