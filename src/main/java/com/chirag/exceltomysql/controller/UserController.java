package com.chirag.exceltomysql.controller;

import com.chirag.exceltomysql.entity.Users;
import com.chirag.exceltomysql.helper.LogSaver;
import com.chirag.exceltomysql.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LogSaver logSaver;

    @PostMapping("/user/register")
    public String registerUser(@Valid @RequestBody Users user) {
        if(userRepo.existsByUsername(user.getUsername())){
            return "Username is already in use";
        }
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        userRepo.save(user);
        logSaver.setLogs("New user registration","Username : "+user.getUsername()+"\nEmail : "+user.getEmail());
        return "User registered successfully";
    }

}
