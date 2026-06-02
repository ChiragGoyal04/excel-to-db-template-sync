package com.chirag.exceltomysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "templates")
public class Templates {

    @Id
    private String id;

    private String templateName;

    private String orgName;

    @OneToMany(mappedBy = "templates",cascade = CascadeType.ALL)
    private List<TempCols> tempCols;
}
