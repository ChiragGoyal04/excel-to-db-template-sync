package com.chirag.exceltomysql.repository;

import com.chirag.exceltomysql.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<Orders, Integer> {
}
