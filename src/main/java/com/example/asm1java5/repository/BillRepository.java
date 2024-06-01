package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByStatus(int status);

    @Transactional
    @Modifying
    @Query("UPDATE Bill b SET b.status = CASE WHEN b.status = 0 THEN 1 ELSE 2 END WHERE b.id = :id")
    void changeStatus(Long id);

    @Modifying
    @Query("UPDATE Bill b SET b.status = 1 WHERE b.id = :id")
    void changeStatusPaySuccess(Integer id);

}
