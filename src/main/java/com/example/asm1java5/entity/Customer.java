package com.example.asm1java5.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private Integer id;
    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    private String code;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "0[0-9]{9}", message = "Phone is invalid")
    private String phone;
    private Integer status;

}
