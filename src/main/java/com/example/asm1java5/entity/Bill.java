package com.example.asm1java5.entity;

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
    private Integer idStaff;
    private Integer idCustomer;
    private Date dateBuy;
    private Integer status;
}
