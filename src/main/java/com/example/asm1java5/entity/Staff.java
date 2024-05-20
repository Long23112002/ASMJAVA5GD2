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
public class Staff {
    private Integer id;
    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 10, message = "Code must be between 3 and 10 characters")
    private String code;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Username is required")
    @Size(min = 5 , max = 50, message = "Name must be between 5 and 50 characters")
    private String username;
    @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    @NotBlank(message = "Password is required")
    private String password;
    private Integer status;
}
