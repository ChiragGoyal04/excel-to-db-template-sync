package com.chirag.exceltomysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products_data")
public class Products {

    @Id
    private Double Product_ID;

    private String Product_Name;
    private String Category;
    private String Price;
    private String Stock;
    private String Supplier;

}
