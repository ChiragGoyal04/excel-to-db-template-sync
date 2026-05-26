package com.chirag.exceltomysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // allow login page
                        .requestMatchers(
                                "/index.html",
                                "/userLogin/**",
                                "/registerUser.html",
                                "/api/user/register"
                        ).permitAll()

                        // secure everything else
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form

                        // your custom page
                        .loginPage("/index.html")

                        // form submits here
                        .loginProcessingUrl("/login")

                        // after successful login
                        .defaultSuccessUrl("/home", true)

                        // if login fails
                        .failureUrl("/index.html?error=invalid")

                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
