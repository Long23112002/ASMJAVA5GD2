package com.example.asm1java5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    private String code;

    @Positive(message = "Price must be greater than 0")
    @NotNull(message = "Price is required")
    private Double price;

    @Positive(message = "Quantity must be greater than 0")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    private Integer status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_size", nullable = false)
    private com.example.asm1java5.entity.Size size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_color", nullable = false)
    private Color color;

    @OneToMany(mappedBy = "productDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BillDetail> listBillDetail;

    @OneToMany(mappedBy = "productDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cart> listCart;


}
