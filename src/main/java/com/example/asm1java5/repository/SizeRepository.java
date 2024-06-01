package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findByNameContainingIgnoreCase(String name);
    List<Size> findByStatus(int status);
    boolean existsByNameIgnoreCase(String name);
    boolean existsByCode(String code);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
    boolean existsByCodeAndIdNot(String code, Integer id);
}
