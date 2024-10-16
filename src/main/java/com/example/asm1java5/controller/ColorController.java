package com.example.asm1java5.controller;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.entity.Product;
import com.example.asm1java5.repository.ColorRepository;
import com.example.asm1java5.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ColorController {
    private final ColorRepository colorRepository;

    @GetMapping("/index")
    public String index(Model model , @RequestParam(value = "page", defaultValue = "0") int page){
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Color> colorPage = colorRepository.findAll(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", colorPage.getTotalPages());
        model.addAttribute("totalItems", colorPage.getTotalElements());
        model.addAttribute("listColor", colorPage.getContent());
        return "color/index";
    }

    @PostMapping("/search")
    public String search(@RequestParam("colorSearch") String name, Model model){
        model.addAttribute("listColor", colorRepository.findByNameContainingIgnoreCase(name));
        return "color/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("color") Color color){
        return "color/create";
    }

    @PostMapping("/store")
    public String store(@Valid Color color , BindingResult result , Model model){
        if (colorRepository.existsByCodeIgnoreCase(color.getCode())){
            result.rejectValue("code", "code", "Color code is exits");
        }


        if(colorRepository.existsByNameIgnoreCase(color.getName().trim())){
            result.rejectValue("name", "name", "Color name is exits");
        }

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
        Color colorEdit = colorRepository.findById(id).get();
        model.addAttribute("color", colorEdit);
        return "color/edit";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid Color color, BindingResult result, Model model){
        if (colorRepository.existsByCodeIgnoreCaseAndIdNot(color.getCode().trim() , color.getId())){
            result.rejectValue("code", "code", "Color code is exits");
        }
        if(colorRepository.existsByNameIgnoreCaseAndIdNot(color.getName().trim() , color.getId())){
            result.rejectValue("name", "name", "Color name is exits");
        }
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "color/edit";
        }
        colorRepository.save(color);
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
