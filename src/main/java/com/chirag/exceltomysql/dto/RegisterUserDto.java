package com.chirag.exceltomysql.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

@Component
public class RegisterUserDto {

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    private String username;

    @Column(unique = true)
    private String password;
}
