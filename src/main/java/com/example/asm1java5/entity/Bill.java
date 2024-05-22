package com.example.asm1java5.entity;

import com.example.asm1java5.custom.ValidStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    private Integer id;
    @NotNull(message = "Staff is required")
    private Integer idStaff;
    @NotNull(message = "Customer is required")
    private Integer idCustomer;
    private Date dateBuy;
    @ValidStatus
    private Integer status;
}
