package com.example.PaginaWebDivisas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir todas las rutas
                .allowedOrigins("http://127.0.0.1:5500", "https://www.cotizacionesdefi.com", "https://127.0.0.1:8443") // Permitir solicitudes desde estos orígenes
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS") // Métodos permitidos
                .allowCredentials(true); // Permitir el uso de credenciales (como cookies o sesiones)
    }
}
