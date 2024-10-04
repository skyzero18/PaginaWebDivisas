package com.example.PaginaWebDivisas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/inicioAdmin/**").authenticated() // Rutas autenticadas
                        .anyRequest().permitAll()  // Permitir todas las demás rutas
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Página de inicio de sesión personalizada
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .csrf().disable();  // Desactivar CSRF en caso de ser API REST, ajusta según tus necesidades

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
