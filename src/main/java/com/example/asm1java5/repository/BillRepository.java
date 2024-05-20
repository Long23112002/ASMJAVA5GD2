package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BillRepository {
    private final List<Bill> listBill;

    public BillRepository() {
        listBill = new ArrayList<>();
        listBill.add(new Bill(1, 1, 1, new Date(), 1));
        listBill.add(new Bill(2, 2, 2, new Date(), 0));
        listBill.add(new Bill(3, 1, 1, new Date(), 1));
        listBill.add(new Bill(4, 2, 2, new Date(), 1));
    }

    public List<Bill> findAll() {
        return listBill;
    }

    public void save(Bill bill) {
        bill.setId(this.listBill.size() + 1);
        this.listBill.add(bill);
    }

    public void deleteById(Integer id) {
        for (Bill bill : listBill) {
            if (bill.getId().equals(id)) {
                listBill.remove(bill);
                break;
            }
        }
    }

    public void update(Bill bill) {
        for (Bill b : listBill) {
            if (b.getId().equals(bill.getId())) {
                b.setIdStaff(bill.getIdStaff());
                b.setIdCustomer(bill.getIdCustomer());
                b.setDateBuy(bill.getDateBuy());
                b.setStatus(bill.getStatus());
                break;
            }
        }
    }
}
