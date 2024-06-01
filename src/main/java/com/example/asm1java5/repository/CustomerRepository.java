package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByPhoneContainingIgnoreCase(String phone);
    boolean existsByCode(String code);
    boolean existsByPhone(String phone);
    boolean existsByCodeAndIdNot(String code, Integer id);
    boolean existsByPhoneAndIdNot(String phone, Integer id);
    List<Customer> findAllByStatus(int status);
}
