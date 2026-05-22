package com.chirag.exceltomysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
public class Orders {

    @Id
    private String id;

    private String Customer_name;
    private String Product;
    private String Quantity;
    private String Total_Amount;
    private String Order_date;
}
