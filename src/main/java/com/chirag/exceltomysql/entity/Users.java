package com.chirag.exceltomysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.chirag.exceltomysql.entity.Logs;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column(unique = true)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt=LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<Logs> logs;

}
