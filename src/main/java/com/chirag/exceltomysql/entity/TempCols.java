package com.chirag.exceltomysql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="temp_cols")
@Data
public class TempCols {

    @Id
    private String id;

    private String Datatype;

    private String ColName;

    @ManyToOne
    @JoinColumn(name = "template_name")
    private Templates templates;
}
