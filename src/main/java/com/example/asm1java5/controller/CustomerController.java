package com.example.asm1java5.controller;


import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public String index(Model model){
        model.addAttribute("listCustomer", customerRepository.findAll());
        return "customer/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("customer") Customer customer){
        return "customer/create";
    }

    @PostMapping("/store")
    public String store(@Valid Customer customer , BindingResult result , Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "customer/create";
        }
        customerRepository.save(customer);
        return "redirect:/customer/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("customer") Customer customer,
                       @PathVariable("id") Integer id, Model model){
        Customer customerEdit = customerRepository.findById(id);
        model.addAttribute("customer", customerEdit);
        return "customer/edit";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid Customer customer, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "customer/edit";
        }
        customerRepository.update(customer);
        return "redirect:/customer/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        customerRepository.deleteById(id);
        return "redirect:/customer/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }


}
