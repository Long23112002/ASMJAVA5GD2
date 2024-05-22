package com.example.asm1java5.controller;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Product;
import com.example.asm1java5.repository.ColorRepository;
import com.example.asm1java5.repository.ProductRepository;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productRepository.findAllPageable(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("listProduct", productPage.getContent());
        return "product/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("product") Product product){
        return "product/create";
    }

    @PostMapping("/search")
    public String search(@RequestParam("productSearchValue") String name, Model model){
        model.addAttribute("listProduct", productRepository.findByName(name));
        return "product/index";
    }


    @PostMapping("/store")
    public String store(@Valid Product product , BindingResult result , Model model){
        if(productRepository.exitsByCode(product.getCode())){
            result.rejectValue("code", "code", "Code is exits");
        }
        if(productRepository.exitsByName(product.getName())){
            result.rejectValue("name", "name", "Name is exits");
        }
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
        if (productRepository.existsByCodeAndIdNot(product.getCode(), product.getId())){
            result.rejectValue("code", "code", "Product code is exits");
        }
        if(productRepository.existsByNameAndIdNot(product.getName(), product.getId())){
            result.rejectValue("name", "name", "Product name is exits");
        }
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
