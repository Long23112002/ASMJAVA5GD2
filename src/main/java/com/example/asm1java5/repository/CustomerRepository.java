package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    private List<Customer> listCustomer;

    public CustomerRepository() {
        listCustomer = new ArrayList<>();
        listCustomer.add(new Customer(1, "C001", "Nguyen Van A", "0901234567", 0));
        listCustomer.add(new Customer(2, "C002", "Nguyen Van B", "0901234568", 1));
        listCustomer.add(new Customer(3, "C003", "Nguyen Van C", "0901234569", 1));
        listCustomer.add(new Customer(4, "C004", "Nguyen Van D", "0901234570", 1));
    }

    public List<Customer> findAll() {
        return listCustomer;
    }

    public List<Customer> findAllByStatusActive() {
        return listCustomer.stream()
                .filter(customer -> customer.getStatus() == 1)
                .collect(Collectors.toList());
    }

    public Page<Customer> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listCustomer.size());
        List<Customer> pageContent = listCustomer.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listCustomer.size());
    }

    public void save(Customer customer) {
        customer.setId(this.listCustomer.size() + 1);
        this.listCustomer.add(customer);
    }

    public void deleteById(Integer id) {
        for (Customer customer : listCustomer) {
            if (customer.getId().equals(id)) {
                listCustomer.remove(customer);
                break;
            }
        }
    }

    public void update(Customer customer) {
        for (Customer c : listCustomer) {
            if (c.getId().equals(customer.getId())) {
                c.setCode(customer.getCode());
                c.setName(customer.getName());
                c.setPhone(customer.getPhone());
                c.setStatus(customer.getStatus());
                break;
            }
        }
    }

    public Customer findById(Integer id) {
        for (Customer customer : listCustomer) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
