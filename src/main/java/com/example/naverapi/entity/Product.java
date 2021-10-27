package com.example.naverapi.entity;

import com.example.naverapi.dto.ProductMypriceRequestDto;
import com.example.naverapi.dto.ProductRequestDto;
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

    public Product (ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }

    public void update (ProductMypriceRequestDto requestDto) {
        this.myprice = requestDto.getMyprice();
    }


}
