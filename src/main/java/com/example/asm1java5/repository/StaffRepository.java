package com.example.asm1java5.repository;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaffRepository {
    private List<Staff> listStaff;

    public StaffRepository() {
        listStaff = new ArrayList<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        listStaff.add(new Staff(1, "SF01","Nguyen Hai Long" , "long", passwordEncoder.encode("1") , Role.ADMIN, 1));
        listStaff.add(new Staff(2, "SF02", "abc", "staff", passwordEncoder.encode("1") , Role.STAFF, 1));
    }

    public List<Staff> findAll() {
        return listStaff;
    }

    public List<Staff> findAllByStatusActive() {
        return listStaff.stream()
                .filter(staff -> staff.getStatus() == 1)
                .collect(Collectors.toList());
    }

    public List<Staff> findByName(String name) {
        return listStaff.stream()
                .filter(staff -> staff.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }


    public Page<Staff> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listStaff.size());
        List<Staff> pageContent = listStaff.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listStaff.size());
    }

    public void save(Staff staff) {
        staff.setId(this.listStaff.size() + 1);
        this.listStaff.add(staff);
    }

    public void deleteById(Integer id) {
        for (Staff staff : listStaff) {
            if (staff.getId().equals(id)) {
                listStaff.remove(staff);
                break;
            }
        }
    }

    public void update(Staff staff) {
        for (Staff c : listStaff) {
            if (c.getId().equals(staff.getId())) {
                c.setCode(staff.getCode());
                c.setName(staff.getName());
                c.setUsername(staff.getUsername());
                c.setPassword(staff.getPassword());
                c.setStatus(staff.getStatus());
                break;
            }
        }
    }

    public Staff findById(Integer id) {
        for (Staff staff : listStaff) {
            if (staff.getId().equals(id)) {
                return staff;
            }
        }
        return null;
    }

    public boolean existsByCode(String code) {
        return listStaff.stream().anyMatch(staff -> staff.getCode().equals(code));
    }

    public boolean existsByUsername(String username) {
        return listStaff.stream().anyMatch(staff -> staff.getUsername().equals(username));
    }

    public boolean existsByCodeAndIdNot(String code, Integer id) {
        return listStaff.stream().anyMatch(staff -> staff.getCode().equals(code) && !staff.getId().equals(id));
    }

    public boolean existsByUsernameAndIdNot(String username, Integer id) {
        return listStaff.stream().anyMatch(staff -> staff.getUsername().equals(username) && !staff.getId().equals(id));
    }

    public Staff findByUsername(String username) {
        for (Staff staff : listStaff) {
            if (staff.getUsername().equals(username)) {
                return staff;
            }
        }
        return null;
    }
}
