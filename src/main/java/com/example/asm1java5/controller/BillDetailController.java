package com.example.asm1java5.controller;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/bill-detail")
@RequiredArgsConstructor
public class BillDetailController {

    private final BillDetailRepository billDetailRepository;
    private final BillRepository billRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<BillDetail> buildPage = billDetailRepository.findAll(pageable);
        List<BillDetail> listBillDetail = billDetailRepository.findAll();
        List<ProductDetail> listProductDetail = productDetailRepository.findAll();
        List<Bill> listBill = billRepository.findAll();
        List<Size> listSize = sizeRepository.findByStatus(1);
        List<Color> listColor = colorRepository.findByStatus(1);
        List<Product> listProduct = productRepository.findAllByStatus(1);

        model.addAttribute("listProductDetail" , listProductDetail);
        model.addAttribute("listBill" , listBill);
        model.addAttribute("listSize" , listSize);
        model.addAttribute("listColor" , listColor);
        model.addAttribute("listProduct" , listProduct);
        model.addAttribute("listBillDetail", listBillDetail);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", buildPage.getTotalPages());
        model.addAttribute("totalItems", buildPage.getTotalElements());
        model.addAttribute("listBillDetail", buildPage.getContent());
        return "bill_detail/index";
    }
}
