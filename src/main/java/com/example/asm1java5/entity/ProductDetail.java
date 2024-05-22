package com.example.asm1java5.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetail {
    private Integer id;
    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    private String code;

    @NotNull(message = "Size is required")
    private Integer idSize;

    @NotNull(message = "Color is required")
    private Integer idColor;

    @NotNull(message = "Product is required")
    private Integer idProduct;

    @Positive(message = "Price must be greater than 0")
    @NotNull(message = "Price is required")
    private Double price;

    @Positive(message = "Quantity must be greater than 0")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    private Integer status;
}
