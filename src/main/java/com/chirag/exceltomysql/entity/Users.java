package com.chirag.exceltomysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "user_details")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

//    @Length(min=6, max=15 ,message = "Password length must be between 6 to 15 character")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
