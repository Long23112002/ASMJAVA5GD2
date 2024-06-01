package com.example.asm1java5.entity;

import com.example.asm1java5.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "password" , columnDefinition = "VARCHAR(MAX)")
    private String password;

    private String role;
    private Integer status;

    @OneToMany(mappedBy = "staff" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> bills;
}
