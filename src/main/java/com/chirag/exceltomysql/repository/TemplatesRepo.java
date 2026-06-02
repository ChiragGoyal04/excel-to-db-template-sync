package com.chirag.exceltomysql.repository;

import com.chirag.exceltomysql.entity.Templates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplatesRepo extends JpaRepository<Templates,String> {
}
