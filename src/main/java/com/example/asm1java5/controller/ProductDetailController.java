package com.example.asm1java5.controller;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Product;
import com.example.asm1java5.entity.ProductDetail;
import com.example.asm1java5.entity.Size;
import com.example.asm1java5.repository.ColorRepository;
import com.example.asm1java5.repository.ProductDetailRepository;
import com.example.asm1java5.repository.ProductRepository;
import com.example.asm1java5.repository.SizeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product-detail")
public class ProductDetailController {

    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;


    @GetMapping("/index")
    public String index(Model model , @RequestParam(value = "page", defaultValue = "0") int page){
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductDetail> productDetailPage = productDetailRepository.findAllByStatus(1,pageable);
        List<Product> listProduct = productRepository.findAllByStatus(1);
        List<Color> listColor = colorRepository.findByStatus(1);
        List<Size> listSize = sizeRepository.findByStatus(1);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productDetailPage.getTotalPages());
        model.addAttribute("totalItems", productDetailPage.getTotalElements());
        model.addAttribute("listProductDetail", productDetailPage.getContent());
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        return "product_detail/index";
    }


    @GetMapping("/create")
    public String create(@ModelAttribute("productDetail") ProductDetail productDetail, Model model) {
        List<Product> listProduct = productRepository.findAllByStatus(1);
        List<Color> listColor = colorRepository.findByStatus(1);
        List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findByStatus(1);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        return "product_detail/create";
    }

    @PostMapping("/store")
    public String store(@Valid ProductDetail productDetail, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Product> listProduct = productRepository.findAllByStatus(1);
            List<Color> listColor = colorRepository.findByStatus(1);
            List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findByStatus(1);
            model.addAttribute("listColor", listColor);
            model.addAttribute("listSize", listSize);
            model.addAttribute("listProduct", listProduct);
            model.addAttribute("errors", getErrorMessages(result));
            log.error("Error: {}", getErrorMessages(result));
            return "product_detail/create";
        }
        productDetailRepository.save(productDetail);
        return "redirect:/product-detail/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("productDetail") ProductDetail productDetail, @PathVariable("id") Integer id, Model model) {
        ProductDetail productDetailEdit = productDetailRepository.findById(Long.valueOf(id)).get();
        List<Product> listProduct = productRepository.findAll();
        List<Color> listColor = colorRepository.findAll();
        List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findByStatus(1);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("productDetail", productDetailEdit);
        return "product_detail/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid ProductDetail productDetail, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(result));
            return "product_detail/edit";
        }
        productDetailRepository.save(productDetail);
        return "redirect:/product-detail/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productDetailRepository.deleteById(Long.valueOf(id));
        return "redirect:/product-detail/index";
    }


    @PostMapping("/filter")
    public String filter(@RequestParam(required = false) Integer searchProduct, @RequestParam(required = false) Integer searchSize,
                         @RequestParam(required = false) Integer searchColor, Model model) {

        List<ProductDetail> filteredProductDetails;

        if (searchProduct != null && searchProduct != 0) {
            if (searchSize != null && searchSize != 0) {
                if (searchColor != null && searchColor != 0) {
                    filteredProductDetails = productDetailRepository.findAllByProductIdAndSizeIdAndColorId(searchSize, searchColor, searchProduct);
                } else {
                    filteredProductDetails = productDetailRepository.findAllByProductIdAndSizeId(searchProduct, searchSize);
                }
            } else if (searchColor != null && searchColor != 0) {
                filteredProductDetails = productDetailRepository.findAllByProductIdAndColorId(searchProduct, searchColor);
            } else {
                filteredProductDetails = productDetailRepository.findAllByProductId(searchProduct);
            }
        } else if (searchSize != null && searchSize != 0) {
            if (searchColor != null && searchColor != 0) {
                filteredProductDetails = productDetailRepository.findAllBySizeIdAndColorId(searchSize, searchColor);
            } else {
                filteredProductDetails = productDetailRepository.findAllBySizeId(searchSize);
            }
        } else if (searchColor != null && searchColor != 0) {
            filteredProductDetails = productDetailRepository.findAllByColorId(searchColor);
        } else {
            filteredProductDetails = productDetailRepository.findAll();
        }


        List<Product> listProduct = productRepository.findAllByStatus(1);
        List<Color> listColor = colorRepository.findByStatus(1);
        List<Size> listSize = sizeRepository.findByStatus(1);

        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listProductDetail", filteredProductDetails);

        return "product_detail/index";
    }



    public static Map<String, String> getErrorMessages(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}


