package com.example.naverapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Product extends Timestamped{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false)
    private String image;

    @Column (nullable = false)
    private String link;

    @Column (nullable = false)
    private int lprice;

    @Column (nullable = false)
    private int myprice;


}
