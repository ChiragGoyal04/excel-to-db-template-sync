package com.chirag.exceltomysql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/current")
    public String currentUser(Principal principal) {
        String usern=principal.getName();
        return usern;
    }
}
