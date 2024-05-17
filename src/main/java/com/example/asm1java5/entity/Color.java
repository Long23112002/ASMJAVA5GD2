package com.example.asm1java5.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color {
    private Integer id;
    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    private String code;
    @Size(min =2, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name is required")
    private String name;

    private Integer status;
}
