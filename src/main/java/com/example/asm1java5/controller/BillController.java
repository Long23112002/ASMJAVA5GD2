package com.example.asm1java5.controller;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.repository.BillRepository;
import com.example.asm1java5.repository.CustomerRepository;
import com.example.asm1java5.repository.StaffRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public String index(Model model ,  @RequestParam(value = "page", defaultValue = "0") int page) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Bill> billPage = billRepository.findAllPageable(pageable);
        List<Staff> listStaff = staffRepository.findAllByStatusActive();
        List<Customer> listCustomer = customerRepository.findAllByStatusActive();
        Map<Integer, String> staffNames = listStaff.stream().collect(Collectors.toMap(Staff::getId, Staff::getName));
        Map<Integer, String> customerNames = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
        Map<Integer, String> phoneCustomer = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getPhone));
        model.addAttribute("staffNames", staffNames);
        model.addAttribute("customerNames", customerNames);
        model.addAttribute("phoneCustomer", phoneCustomer);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", billPage.getTotalPages());
        model.addAttribute("totalItems", billPage.getTotalElements());
        model.addAttribute("listBill", billPage.getContent());
        return "bill/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("bill") Bill bill,
                       @PathVariable("id") Integer id, Model model){
        List<Customer> listCustomer = customerRepository.findAllByStatusActive();
        List<Staff> listStaff = staffRepository.findAllByStatusActive();
        Bill billEdit = billRepository.findById(id);
        model.addAttribute("listCustomer", listCustomer);
        model.addAttribute("listStaff", listStaff);
        model.addAttribute("bill", billEdit);
        return "bill/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid Bill bill, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "bill/edit";
        }
        billRepository.update(bill);
        return "redirect:/bill/index";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam Integer statusSearch, Model model) {
        if(statusSearch == -1) {
            List<Bill> listBill = billRepository.findAll();
            List<Staff> listStaff = staffRepository.findAllByStatusActive();
            List<Customer> listCustomer = customerRepository.findAllByStatusActive();
            Map<Integer, String> staffNames = listStaff.stream().collect(Collectors.toMap(Staff::getId, Staff::getName));
            Map<Integer, String> customerNames = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
            model.addAttribute("staffNames", staffNames);
            model.addAttribute("customerNames", customerNames);
            model.addAttribute("valueSearch" , statusSearch);
            model.addAttribute("listBill",listBill);
            return "bill/index";
        }else {
            List<Bill> listBillSearch = billRepository.findBillByStatus(statusSearch);
            List<Staff> listStaff = staffRepository.findAllByStatusActive();
            List<Customer> listCustomer = customerRepository.findAllByStatusActive();
            Map<Integer, String> staffNames = listStaff.stream().collect(Collectors.toMap(Staff::getId, Staff::getName));
            Map<Integer, String> customerNames = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
            model.addAttribute("valueSearch" , statusSearch);
            model.addAttribute("staffNames", staffNames);
            model.addAttribute("customerNames", customerNames);
            model.addAttribute("listBill",listBillSearch);
            return "bill/index";
        }
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

}
