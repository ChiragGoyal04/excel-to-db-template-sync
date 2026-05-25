package com.chirag.exceltomysql.repository;

import com.chirag.exceltomysql.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
