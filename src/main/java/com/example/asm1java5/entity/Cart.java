package com.example.asm1java5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Integer id;

    private Integer idOder;

    private List<ProductDetail> productDetailList;

    private Integer quantity;

    private Double price;

    private Double total;
}
