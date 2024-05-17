package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffRepository {
    private List<Staff> listStaff;

    public StaffRepository() {
        listStaff = new ArrayList<>();
        listStaff.add(new Staff(1, "SF01", "Long", "0888880243", 1));
        listStaff.add(new Staff(2, "SF02", "Test01", "099999999", 1));
    }

    public List<Staff> findAll() {
        return listStaff;
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
}
