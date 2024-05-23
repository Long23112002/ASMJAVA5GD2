package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Bill;
import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BillRepository {
    private final List<Bill> listBill;

    public BillRepository() {
        listBill = new ArrayList<>();
        listBill.add(new Bill(1, 1, 1 , new Date() ,0.0, 1));
        listBill.add(new Bill(2, 2, 2, new Date(),0.0, 0));
        listBill.add(new Bill(3, 1, 1, new Date(),0.0, 1));
        listBill.add(new Bill(4, 2, 2, new Date(),0.0, 1));
    }

    public void save(Bill bill) {
        bill.setId(listBill.size() + 1);
        listBill.add(bill);
    }

    public void changeStatus(Integer id) {
        for (Bill bill : listBill) {
            if (bill.getId().equals(id)) {
                bill.setStatus(-1);
                break;
            }
        }
    }

    public void changeStatusPaySuccess(Integer id) {
        for (Bill bill : listBill) {
            if (bill.getId().equals(id)) {
                bill.setStatus(1);
                break;
            }
        }
    }

    public List<Bill> findAll() {
        return listBill;
    }

    public List<Bill> findBillByStatus(Integer status) {
        return listBill.stream()
                .filter(bill -> bill.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public Page<Bill> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listBill.size());
        List<Bill> pageContent = listBill.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listBill.size());
    }

    public Bill findById(Integer id) {
        for (Bill bill : listBill) {
            if (bill.getId().equals(id)) {
                return bill;
            }
        }
        return null;
    }

    public void update(Bill bill) {
        for (Bill b : listBill) {
            if (b.getId().equals(bill.getId())) {
                b.setIdStaff(bill.getIdStaff());
                b.setIdCustomer(bill.getIdCustomer());
                b.setStatus(bill.getStatus());
                break;
            }
        }
    }


}
