package com.chirag.exceltomysql.repository;


import com.chirag.exceltomysql.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Double> {
}
