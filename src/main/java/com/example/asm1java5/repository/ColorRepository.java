package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findByNameContainingIgnoreCase(String name);
    List<Color> findByStatus(int status);
    boolean existsByNameIgnoreCase(String name);
    boolean existsByCodeIgnoreCase(String code);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
    boolean existsByCodeIgnoreCaseAndIdNot(String code, Integer id);
}
