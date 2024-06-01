package com.example.asm1java5.controller;


import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.repository.CustomerRepository;
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
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("totalItems", customerPage.getTotalElements());
        model.addAttribute("listCustomer", customerPage.getContent());
        return "customer/index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("customerValue") String phone, Model model) {
        model.addAttribute("listCustomer", customerRepository.findByPhoneContainingIgnoreCase(phone));
        return "customer/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer) {
        return "customer/create";
    }

    @PostMapping("/store")
    public String store(@Valid Customer customer, BindingResult result, Model model) {
        if (customerRepository.existsByCode(customer.getCode())) {
            result.rejectValue("code", "code", "Code is exits");
        }
        if (customerRepository.existsByPhone(customer.getPhone())) {
            result.rejectValue("phone", "phone", "Phone is exits");
        }
        if (result.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(result));
            return "customer/create";
        }
        customerRepository.save(customer);
        return "redirect:/customer/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("customer") Customer customer, @PathVariable("id") Integer id, Model model) {
        Customer customerEdit = customerRepository.findById(id).get();
        model.addAttribute("customer", customerEdit);
        return "customer/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid Customer customer, BindingResult result, Model model) {
        if (customerRepository.existsByCodeAndIdNot(customer.getCode(), customer.getId())) {
            result.rejectValue("code", "code", "Code is exits");
        }
        if (customerRepository.existsByPhoneAndIdNot(customer.getPhone(), customer.getId())) {
            result.rejectValue("phone", "phone", "Phone is exits");
        }
        if (result.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(result));
            return "customer/edit";
        }
        customerRepository.save(customer);
        return "redirect:/customer/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/customer/index";
    }

    public static Map<String, String> getErrorMessages(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }


}
