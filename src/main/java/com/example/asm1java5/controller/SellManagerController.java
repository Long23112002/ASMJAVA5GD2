package com.example.asm1java5.controller;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sell-manager")
@RequiredArgsConstructor
public class SellManagerController {

    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ColorRepository colorRepository;
    private final CustomerRepository customerRepository;
    private final BillRepository billRepository;
    private final StaffRepository staffRepository;

    @GetMapping("/index")
    public String index(Model model) {

        List<Product> listProduct = productRepository.findAllByStatusActive();
        List<Color> listColor = colorRepository.findAllByStatusActive();
        List<Size> listSize = sizeRepository.findAllByStatusActive();
        List<Staff> listStaff = staffRepository.findAllByStatusActive();
        List<Customer> listCustomer = customerRepository.findAllByStatusActive();
        Map<Integer, String> productNames = listProduct.stream().collect(Collectors.toMap(Product::getId, Product::getName));
        Map<Integer, String> colorNames = listColor.stream().collect(Collectors.toMap(Color::getId, Color::getName));
        Map<Integer, String> sizeNames = listSize.stream().collect(Collectors.toMap(Size::getId, Size::getName));
        Map<Integer, String> customerNames = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
        Map<Integer, String> staffNames = listStaff.stream().collect(Collectors.toMap(Staff::getId, Staff::getName));
        model.addAttribute("staffNames", staffNames);
        model.addAttribute("customerNames", customerNames);
        model.addAttribute("listCustomer", listCustomer);
        model.addAttribute("productNames", productNames);
        model.addAttribute("colorNames", colorNames);
        model.addAttribute("sizeNames", sizeNames);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listProductDetail", productDetailRepository.findAllByStatusActive());
        return "manager_sell/index";
    }


    @PostMapping("/filter")
    public String filter(@RequestParam(required = false) Integer searchProduct, @RequestParam(required = false) Integer searchSize, @RequestParam(required = false) Integer searchColor, Model model) {

        List<ProductDetail> filteredProductDetails = productDetailRepository.findAllByStatusActive();

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
        List<Staff> listStaff = staffRepository.findAllByStatusActive();
        List<Customer> listCustomer = customerRepository.findAllByStatusActive();

        Map<Integer, String> productNames = listProduct.stream().collect(Collectors.toMap(Product::getId, Product::getName));
        Map<Integer, String> colorNames = listColor.stream().collect(Collectors.toMap(Color::getId, Color::getName));
        Map<Integer, String> sizeNames = listSize.stream().collect(Collectors.toMap(Size::getId, Size::getName));
        Map<Integer, String> customerNames = listCustomer.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
        Map<Integer, String> staffNames = listStaff.stream().collect(Collectors.toMap(Staff::getId, Staff::getName));

        model.addAttribute("staffNames", staffNames);
        model.addAttribute("customerNames", customerNames);
        model.addAttribute("productNames", productNames);
        model.addAttribute("colorNames", colorNames);
        model.addAttribute("sizeNames", sizeNames);
        model.addAttribute("idColor", searchColor);
        model.addAttribute("idSize", searchSize);
        model.addAttribute("idProduct", searchProduct);
        model.addAttribute("listCustomer", listCustomer);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listProductDetail", filteredProductDetails);

        return "manager_sell/index";
    }


    @GetMapping("/choose-customer/{id}")
    public String choseCustomer(@PathVariable("id") Integer id, Model model, HttpSession session) {
        session.removeAttribute("errorCustomer");
        Customer customer = customerRepository.findById(id);
        session.setAttribute("customerInfo", customer);
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/create-order")
    public String createOder(HttpSession session ) {
        Customer customer = (Customer) session.getAttribute("customerInfo");
        if (customer  == null){
            session.setAttribute("errorCustomer", "Please choose customer");
            return "redirect:/sell-manager/index";
        }

        Bill bill = new Bill();
        bill.setIdCustomer(customer.getId());
        bill.setStatus(0);
        bill.setIdStaff(1);
        bill.setDateBuy(new Date());
        billRepository.save(bill);
        List<Bill> billList = (List<Bill>) session.getAttribute("billList");
        if (billList == null) {
            billList = new ArrayList<>();
        }
        billList.add(bill);
        session.setAttribute("billList", billList);
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/cancel-order/{id}")
    public String cancelOrder(HttpSession session , @PathVariable("id") Integer id) {
        List<Bill> billList = (List<Bill>) session.getAttribute("billList");

        if (billList != null) {
            billList = billList.stream().filter(bill -> !bill.getId().equals(id)).collect(Collectors.toList());
            session.setAttribute("billList", billList);
            session.setAttribute("idOrderChose", null);
            billRepository.changeStatus(id);
        }
        return "redirect:/sell-manager/index";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@ModelAttribute("customer") Customer customer, HttpSession session) {
        customerRepository.save(customer);
//        session.setAttribute("customerInfo", customer);
        return "redirect:/sell-manager/index";
    }


    @GetMapping("/choose-oder/{id}")
    public String chooseOder(@PathVariable("id") Integer id, HttpSession session) {
        session.setAttribute("idOrderChose", id);
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/show-model-product")
    public String showModelProduct(@RequestParam("id") Integer id, Model model) {
        ProductDetail productDetail = productDetailRepository.findById(id);
        model.addAttribute("productDetail", productDetail);
        return "manager_sell/model";
    }
}
