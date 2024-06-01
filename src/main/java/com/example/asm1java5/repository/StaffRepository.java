package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    List<Staff> findAllByStatus(int status);
    Page<Staff> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Staff> findAllByNameContains(String name);
    boolean existsByCode(String code);
    boolean existsByUsername(String username);
    boolean existsByCodeAndIdNot(String code, Integer id);
    boolean existsByUsernameAndIdNot(String username, Integer id);
    Staff findByUsername(String username);
}
