package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByStatus(int status);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Product> findAllByNameContains(String name);
    boolean existsByName(String name);
    boolean existsByCode(String code);
    boolean existsByNameAndIdNot(String name, Integer id);
    boolean existsByCodeAndIdNot(String code, Integer id);
}
