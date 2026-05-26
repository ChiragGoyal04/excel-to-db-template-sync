package com.chirag.exceltomysql.controller;

import com.chirag.exceltomysql.entity.Users;
import com.chirag.exceltomysql.helper.LogSaver;
import com.chirag.exceltomysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LogSaver logSaver;

    @PostMapping("/user/login")
    public String login(@RequestParam String random_username,@RequestParam String random_password) {
        if(userRepo.existsByUsername(random_username)) {
            Users us=userRepo.findByUsername(random_username);
            if(us!= null && passwordEncoder.matches(random_password, us.getPassword())) {
                logSaver.setLogs("Logged In","username : "+random_username );
                return "redirect:/userLogin/Userlogin.html";
            }
            return "redirect:/index.html?error=invalid";
        }
        return "redirect:/index.html?error=notexist";
    }
}
