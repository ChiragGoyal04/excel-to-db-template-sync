package com.chirag.exceltomysql.repository;

import com.chirag.exceltomysql.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRespository extends JpaRepository<Logs,Long> {
}
