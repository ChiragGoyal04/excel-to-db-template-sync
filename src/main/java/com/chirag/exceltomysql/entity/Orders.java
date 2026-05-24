package com.chirag.exceltomysql.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "orders_data")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Customer_name;
    private String Product;
    private String Quantity;
    private String Total_Amount;
    private String Order_date;
}
