package com.example.asm1java5.controller;

import com.example.asm1java5.entity.Customer;
import com.example.asm1java5.entity.Size;
import com.example.asm1java5.entity.Staff;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    @GetMapping("/index")
    public String index(Model model , @RequestParam(value = "page", defaultValue = "0") int page){
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Staff> staffPage = staffRepository.findAllPageable(pageable);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", staffPage.getTotalPages());
        model.addAttribute("totalItems", staffPage.getTotalElements());
        model.addAttribute("listStaff", staffPage.getContent());
        return "staff/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("staff") Staff staff){
        return "staff/create";
    }

    @PostMapping("/search")
    public String search(@RequestParam("staffValueSearch") String name, Model model){
        List<Staff> listStaff = staffRepository.findByName(name);
        model.addAttribute("listStaff", listStaff);
        return "staff/index";
    }


    @PostMapping("/store")
    public String store(@Valid Staff staff , BindingResult result , Model model){
        if(staffRepository.existsByCode(staff.getCode().trim())){
            result.rejectValue("code", "code", "Code is exits");
        }
        if(staffRepository.existsByUsername(staff.getUsername().trim())){
            result.rejectValue("username", "username", "Username is exits");
        }
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "staff/create";
        }
        staffRepository.save(staff);
        return "redirect:/staff/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("staff") Staff staff,
                       @PathVariable("id") Integer id, Model model){
        Staff staffEdit = staffRepository.findById(id);
        model.addAttribute("staff", staffEdit);
        return "staff/edit";
    }

    @PostMapping("/update/{id}")
    public String update( @Valid Staff staff, BindingResult result, Model model){
        if(staffRepository.existsByCodeAndIdNot(staff.getCode(), staff.getId())){
            result.rejectValue("code", "code", "Code is exits");
        }
        if(staffRepository.existsByUsernameAndIdNot(staff.getUsername(), staff.getId())){
            result.rejectValue("username", "username", "Username is exits");
        }
        if (result.hasErrors()){
            model.addAttribute("errors", getErrorMessages(result));
            return "staff/edit";
        }
        staffRepository.update(staff);
        return "redirect:/staff/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        staffRepository.deleteById(id);
        return "redirect:/staff/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

}
