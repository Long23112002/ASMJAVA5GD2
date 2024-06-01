package com.example.asm1java5.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Integer idCard;
    private String codeProduct;
    private String nameProduct;
    private String nameColor;
    private String nameSize;
    private Integer quantity;
    private Double price;
    private Double total;
}
