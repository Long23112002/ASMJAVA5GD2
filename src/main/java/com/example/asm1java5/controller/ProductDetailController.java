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
    public String index(Model model) {
        List<Product> listProduct = productRepository.findAll();
        List<Color> listColor = colorRepository.findAll();
        List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findAll();
        Map<Integer, String> productNames = listProduct.stream().collect(Collectors.toMap(Product::getId, Product::getName));
        Map<Integer, String> colorNames = listColor.stream().collect(Collectors.toMap(Color::getId, Color::getName));
        Map<Integer, String> sizeNames = listSize.stream().collect(Collectors.toMap(Size::getId, Size::getName));
        model.addAttribute("productNames", productNames);
        model.addAttribute("colorNames", colorNames);
        model.addAttribute("sizeNames", sizeNames);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listProductDetail", productDetailRepository.findAll());
        return "product_detail/index";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("productDetail") ProductDetail productDetail, Model model) {
        List<Product> listProduct = productRepository.findAll();
        List<Color> listColor = colorRepository.findAll();
        List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findAll();
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        return "product_detail/create";
    }

    @PostMapping("/store")
    public String store(@Valid ProductDetail productDetail, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Product> listProduct = productRepository.findAll();
            List<Color> listColor = colorRepository.findAll();
            List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findAll();
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
        ProductDetail productDetailEdit = productDetailRepository.findById(id);
        List<Product> listProduct = productRepository.findAll();
        List<Color> listColor = colorRepository.findAll();
        List<com.example.asm1java5.entity.Size> listSize = sizeRepository.findAll();
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
        productDetailRepository.update(productDetail);
        return "redirect:/product-detail/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productDetailRepository.deleteById(id);
        return "redirect:/product-detail/index";
    }


    @PostMapping("/filter")
    public String filter(@RequestParam(required = false) Integer searchProduct, @RequestParam(required = false) Integer searchSize, @RequestParam(required = false) Integer searchColor, Model model) {

        List<ProductDetail> filteredProductDetails = productDetailRepository.findAll();

        if (searchProduct != null && searchProduct != 0) {
            filteredProductDetails = filteredProductDetails.stream().filter(pd -> pd.getIdProduct().equals(searchProduct)).collect(Collectors.toList());
        }

        if (searchSize != null && searchSize != 0) {
            filteredProductDetails = filteredProductDetails.stream().filter(pd -> pd.getIdSize().equals(searchSize)).collect(Collectors.toList());
        }

        if (searchColor != null && searchColor != 0) {
            filteredProductDetails = filteredProductDetails.stream().filter(pd -> pd.getIdColor().equals(searchColor)).collect(Collectors.toList());
        }


        List<Product> listProduct = productRepository.findAll();
        List<Color> listColor = colorRepository.findAll();
        List<Size> listSize = sizeRepository.findAll();

        Map<Integer, String> productNames = listProduct.stream().collect(Collectors.toMap(Product::getId, Product::getName));
        Map<Integer, String> colorNames = listColor.stream().collect(Collectors.toMap(Color::getId, Color::getName));
        Map<Integer, String> sizeNames = listSize.stream().collect(Collectors.toMap(Size::getId, Size::getName));

        model.addAttribute("productNames", productNames);
        model.addAttribute("colorNames", colorNames);
        model.addAttribute("sizeNames", sizeNames);

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
