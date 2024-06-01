package com.example.asm1java5.repository;

import com.example.asm1java5.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {

    List<BillDetail> findAllByStatus(int status);
    @Modifying  // Required for update queries
    @Query("UPDATE Bill b SET b.total = b.total + :adjustment WHERE b.id = :billId")
    void updateBillTotal(Integer billId, double adjustment);

}
