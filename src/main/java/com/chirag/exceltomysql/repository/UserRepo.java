package com.chirag.exceltomysql.repository;

import com.chirag.exceltomysql.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);

    Users findByUsername(String username);
}
