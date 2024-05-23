package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Bill;
import com.example.asm1java5.entity.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BillDetailRepository {

    private final List<BillDetail> listBillDetail;

    public BillDetailRepository() {
        listBillDetail = new ArrayList<>();
        listBillDetail.add(new BillDetail(1, 1, 1, 1, 100.0, 1));
        listBillDetail.add(new BillDetail(2, 2, 2, 2, 200.0, 0));
        listBillDetail.add(new BillDetail(3, 1, 1, 1, 150.0, 1));
        listBillDetail.add(new BillDetail(4, 2, 2, 2, 255.0, 1));
    }

    public List<BillDetail> findAll() {
        return listBillDetail;
    }

    public void save(BillDetail billDetail) {
        billDetail.setId(listBillDetail.size() + 1);
        listBillDetail.add(billDetail);
    }

    public List<BillDetail> findBillByStatus(Integer status) {
        return listBillDetail.stream()
                .filter(bill -> bill.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public Page<BillDetail> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listBillDetail.size());
        List<BillDetail> pageContent = listBillDetail.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listBillDetail.size());
    }

    public BillDetail findById(Integer id) {
        for (BillDetail bill : listBillDetail) {
            if (bill.getId().equals(id)) {
                return bill;
            }
        }
        return null;
    }
}
