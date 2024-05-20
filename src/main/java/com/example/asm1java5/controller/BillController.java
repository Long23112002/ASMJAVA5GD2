package com.example.asm1java5.controller;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.repository.BillRepository;
import com.example.asm1java5.repository.CustomerRepository;
import com.example.asm1java5.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {

    private final BillRepository billRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Staff> listStaff = staffRepository.findAllByStatusActive();
        List<Customer> listCustomer = customerRepository.findAllByStatusActive();
        List<Bill> listBill = billRepository.findAll();

        Map<Integer, String> staffNames = listStaff.stream().collect(Collectors.toMap(Staff::getId, Staff::getName));
        Map<Integer, String> customerNames = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
        model.addAttribute("staffNames", staffNames);
        model.addAttribute("customerNames", customerNames);
        model.addAttribute("listBill",listBill);
        return "bill/index";
    }

}
