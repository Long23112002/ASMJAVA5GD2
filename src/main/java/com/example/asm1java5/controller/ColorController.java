package com.example.asm1java5.controller;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.entity.Product;
import com.example.asm1java5.repository.ColorRepository;
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
@RequestMapping("/color")
public class ColorController {
    private final ColorRepository colorRepository;

    @GetMapping("/index")
    public String index(Model model , @RequestParam(value = "page", defaultValue = "0") int page){
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Color> colorPage = colorRepository.findAllPageable(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", colorPage.getTotalPages());
        model.addAttribute("totalItems", colorPage.getTotalElements());
        model.addAttribute("listColor", colorPage.getContent());
        return "color/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("color") Color color){
        return "color/create";
    }

    @PostMapping("/store")
    public String store(@Valid Color color , BindingResult result , Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "color/create";
        }
        colorRepository.save(color);
        return "redirect:/color/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("color") Color color,
                       @PathVariable("id") Integer id, Model model){
        Color colorEdit = colorRepository.findById(id);
        model.addAttribute("color", colorEdit);
        return "color/edit";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid Color color, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "color/edit";
        }
        colorRepository.update(color);
        return "redirect:/color/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        colorRepository.deleteById(id);
        return "redirect:/color/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
