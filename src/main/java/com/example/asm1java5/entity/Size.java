package com.example.asm1java5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Code is required")
    @jakarta.validation.constraints.Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    private String code;
    @NotBlank(message = "Name is required")
    @jakarta.validation.constraints.Size(min = 1, max = 4, message = "Name must be between 1 and 4 characters")
    private String name;
    private Integer status;

    @OneToMany(mappedBy = "size" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductDetail> listProductDetail;
}
