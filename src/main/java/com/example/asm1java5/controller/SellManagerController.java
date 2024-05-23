package com.example.asm1java5.controller;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
    private final CartRepository cartRepository;
    private final BillDetailRepository billDetailRepository;

    @GetMapping("/index")
    public String index(Model model, @AuthenticationPrincipal User user, HttpSession session) {
        String roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
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
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");

        if (idOrder != null) {
            List<Cart> listCard = cartRepository.findAllByIdOder(idOrder);
            model.addAttribute("listCart", listCard);
        }

        session.setAttribute("userName", user.getUsername());
        session.setAttribute("role", roles);
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
    public String createOder(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customerInfo");
        String userNameStaff = (String) session.getAttribute("userName");
        Staff staff = staffRepository.findByUsername(userNameStaff);
        if (customer == null) {
            session.setAttribute("errorCustomer", "Please choose customer");
            return "redirect:/sell-manager/index";
        }

        Bill bill = new Bill();
        bill.setIdCustomer(customer.getId());
        bill.setStatus(0);
        bill.setIdStaff(staff.getId());
        bill.setDateBuy(new Date());
        bill.setTotal(0.0);
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
    public String cancelOrder(HttpSession session, @PathVariable("id") Integer id) {
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
        return "redirect:/sell-manager/index";
    }


    @GetMapping("/choose-oder/{id}")
    public String chooseOder(@PathVariable("id") Integer id, HttpSession session, Model model) {
        Bill bill = billRepository.findById(id);
        session.setAttribute("idOrderChose", bill.getId());
        session.setAttribute("bill", bill);
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/show-model-product")
    public String showModelProduct(@RequestParam("id") Integer id, Model model) {
        ProductDetail productDetail = productDetailRepository.findById(id);
        model.addAttribute("productDetail", productDetail);
        return "manager_sell/model";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("code") String code,
                            @RequestParam("quantity") Integer quantity,
                            @RequestParam("totalMoney") Double total,
                            HttpSession session) {
        log.info("code: {}", code);
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        log.info("idOrder: {}", idOrder);
        if (idOrder != null) {
            Bill bill = billRepository.findById(idOrder);
            ProductDetail productDetail = productDetailRepository.findByCode(code);
            if (bill != null && productDetail != null) {
                Cart existingCart = cartRepository.findByOrderIdAndProductDetailId(idOrder, productDetail.getId());
                if (existingCart != null) {
                    existingCart.setQuantity(existingCart.getQuantity() + quantity);
                    existingCart.setTotal(existingCart.getTotal() + total);
                    productDetailRepository.updateQuantity(code, quantity);
                    cartRepository.update(existingCart);
                } else {
                    Cart cart = new Cart();
                    cart.setIdOder(idOrder);
                    cart.setProductDetailList(Collections.singletonList(productDetail));
                    cart.setQuantity(quantity);
                    cart.setPrice(productDetail.getPrice());
                    cart.setTotal(total);
                    productDetailRepository.updateQuantity(code, quantity);
                    cartRepository.save(cart);
                }
                bill.setTotal(bill.getTotal() + total);
                session.setAttribute("billTotal", bill.getTotal());
                billRepository.update(bill);
            }
        }
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable("id") Integer id, HttpSession session) {
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        if (idOrder != null) {
            Cart cart = cartRepository.findById(id);
            if (cart != null) {
                List<ProductDetail> productDetails = cart.getProductDetailList();
                for (ProductDetail productDetail : productDetails) {
                    String code = productDetail.getCode();
                    Integer quantity = cart.getQuantity();
                    cartRepository.deleteById(id);
                    productDetailRepository.updateQuantityClear(code, quantity);
                    updateBillTotal(idOrder, -quantity * productDetail.getPrice());
                }
            }
        }
        return "redirect:/sell-manager/index";
    }


    @GetMapping("/clear-all")
    public String clearAll(HttpSession session) {
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        if (idOrder != null) {
            List<Cart> cartItems = cartRepository.findAllByIdOder(idOrder);
            double totalAmount = 0;
            for (Cart cartItem : cartItems) {
                List<ProductDetail> productDetails = cartItem.getProductDetailList();
                for (ProductDetail productDetail : productDetails) {
                    String code = productDetail.getCode();
                    Integer quantity = cartItem.getQuantity();
                    productDetailRepository.updateQuantityClear(code, quantity);
                    totalAmount += quantity * productDetail.getPrice();
                }
            }
            updateBillTotal(idOrder, -totalAmount);
            cartRepository.clearAllCardByOrderId(idOrder);
        }
        return "redirect:/sell-manager/index";
    }

    @PostMapping("/pay")
    public String pay(HttpSession session) {
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        if (idOrder != null) {
            List<Cart> cartItems = cartRepository.findAllByIdOder(idOrder);
            for (Cart cartItem : cartItems) {
                List<ProductDetail> productDetails = cartItem.getProductDetailList();
                for (ProductDetail productDetail : productDetails) {
                    BillDetail billDetail = new BillDetail();
                    billDetail.setIdBill(idOrder);
                    billDetail.setIdProductDetail(productDetail.getId());
                    billDetail.setQuantity(cartItem.getQuantity());
                    billDetail.setPrice(productDetail.getPrice());
                    billDetail.setStatus(1);
                    billDetailRepository.save(billDetail);
                }

            }

            billRepository.changeStatusPaySuccess(idOrder);
            cartRepository.clearAllCardByOrderId(idOrder);
            session.setAttribute("idOrderChose", null);
            session.setAttribute("billTotal", 0.0);
        }
        return "redirect:/sell-manager/index";
    }


    private void updateBillTotal(Integer idOrder, double amount) {
        Bill bill = billRepository.findById(idOrder);
        if (bill != null) {
            bill.setTotal(bill.getTotal() + amount);
            billRepository.update(bill);
        }
    }

}
