package com.example.demo.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Board {

    @Id // PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity == mysql, mariadb에서 사용함
    private Integer id;

    private String title;

    private String content;

}
