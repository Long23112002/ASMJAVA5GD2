package com.example.asm1java5.entity;

import com.example.asm1java5.custom.ValidStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dateBuy;
    private Double total;
    @ValidStatus
    private Integer status;

    @OneToMany(mappedBy = "bill" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BillDetail> listBillDetail;



    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "id_staff", nullable = false)
    private Staff staff;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Cart> listCart;


}
