package com.example.asm1java5.controller;


import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Size;
import com.example.asm1java5.repository.ColorRepository;
import com.example.asm1java5.repository.SizeRepository;
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
@RequestMapping("/size")
public class SizeController {
    private final SizeRepository sizeRepository;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("listSize", sizeRepository.findAll());
        return "size/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("size") Size size){
        return "size/create";
    }

    @PostMapping("/store")
    public String store(@Valid Size size , BindingResult result , Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "size/create";
        }
        sizeRepository.save(size);
        return "redirect:/size/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("size") Size size,
                       @PathVariable("id") Integer id, Model model){
        Size sizeEdit = sizeRepository.findById(id);
        model.addAttribute("size", sizeEdit);
        return "size/edit";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid Size size, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "size/edit";
        }
        sizeRepository.update(size);
        return "redirect:/size/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        sizeRepository.deleteById(id);
        return "redirect:/size/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
