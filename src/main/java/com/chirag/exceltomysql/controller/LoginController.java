package com.chirag.exceltomysql.controller;

import com.chirag.exceltomysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private LogSaver logSaver;

//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }

    @GetMapping("/home")
    public String homePage() {
        return "redirect:/UserLogin/Userlogin.html";
    }





//    public String login(@RequestParam String random_username,@RequestParam String random_password) {
//        if(userRepo.existsByUsername(random_username)) {
//            Users us=userRepo.findByUsername(random_username);
//            if(us!= null && passwordEncoder.matches(random_password, us.getPassword())) {
//                logSaver.setLogs("Logged In","username : "+random_username );
//                return "redirect:/userLogin/Userlogin.html";
//            }
//            return "redirect:/index.html?error=invalid";
//        }
//        return "redirect:/index.html?error=notexist";
//    }
}
