package com.chirag.exceltomysql.service;


import com.chirag.exceltomysql.entity.Logs;
import com.chirag.exceltomysql.entity.Users;
import com.chirag.exceltomysql.helper.LogSaver;
import com.chirag.exceltomysql.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LogSaver logSaver;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user =
                userRepo.findByUsername(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found"
                                ));

        logSaver.setLogs("Logged In","username : "+username,user);

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

//        return user.build();
    }
}
