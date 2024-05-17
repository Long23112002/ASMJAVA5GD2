package com.example.asm1java5.controller;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Product;
import com.example.asm1java5.repository.ColorRepository;
import com.example.asm1java5.repository.ProductRepository;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("listProduct", productRepository.findAll());
        return "product/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("product") Product product){
        return "product/create";
    }

    @PostMapping("/store")
    public String store(@Valid Product product , BindingResult result , Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "product/create";
        }
        productRepository.save(product);
        return "redirect:/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("product") Product product,
                       @PathVariable("id") Integer id, Model model){
        Product productEdit = productRepository.findById(id);
        model.addAttribute("product", productEdit);
        return "product/edit";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "product/edit";
        }
        productRepository.update(product);
        return "redirect:/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        productRepository.deleteById(id);
        return "redirect:/product/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
