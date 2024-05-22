package com.example.asm1java5.entity;

import com.example.asm1java5.custom.ValidStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetail {
    private Integer id;
    private Integer idBill;
    private Integer idProductDetail;
    private Integer quantity;
    private Double price;
    @ValidStatus
    private Integer status;
}
