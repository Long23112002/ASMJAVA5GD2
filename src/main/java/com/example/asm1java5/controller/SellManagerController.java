package com.example.asm1java5.controller;

import com.example.asm1java5.entity.*;
import com.example.asm1java5.enums.Status;
import com.example.asm1java5.enums.StatusOder;
import com.example.asm1java5.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(Model model, @AuthenticationPrincipal User user, HttpSession session, @RequestParam(value = "page", defaultValue = "0") int page) {
        String roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));
        List<Product> listProduct = (List<Product>) session.getAttribute("listProduct");
        if (listProduct == null) {
            listProduct = productRepository.findAllByStatus(Status.ACTIVE.getValue());
        }

        List<Color> listColor = (List<Color>) session.getAttribute("listColor");
        if (listColor == null) {
            listColor = colorRepository.findByStatus(Status.ACTIVE.getValue());
        }

        List<Size> listSize = (List<Size>) session.getAttribute("listSize");
        if (listSize == null) {
            listSize = sizeRepository.findByStatus(Status.ACTIVE.getValue());
        }

        List<ProductDetail> listProductDetail = (List<ProductDetail>) session.getAttribute("listProductDetail");
        if (listProductDetail == null) {
            int pageSize = 3;
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<ProductDetail> productDetailPage = productDetailRepository.findAllByStatus(1, pageable);
        }

        List<Staff> listStaff = staffRepository.findAllByStatus(Status.ACTIVE.getValue());
        List<Customer> listCustomer = customerRepository.findAllByStatus(Status.ACTIVE.getValue());
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        List<Bill> billList = (List<Bill>) session.getAttribute("billList");

        if (idOrder != null) {
            List<Cart> cartItems = cartRepository.findAllByBill_Id(idOrder);
            model.addAttribute("cartItems", cartItems);
            if (idOrder != 0) {
                if (billList != null) {
                    for (Bill bill : billList) {
                        if (bill.getId().equals(idOrder)) {
                            session.setAttribute("bill", bill);
                            session.setAttribute("staffName", bill.getStaff().getName());
                            session.setAttribute("dateBuy", bill.getDateBuy());
                            session.setAttribute("total", bill.getTotal());
                        }
                    }
                }
            } else {
                session.setAttribute("bill", null);
                session.setAttribute("dateBuy", null);
                session.setAttribute("total", 0);
            }
        }

        session.setAttribute("userName", user.getUsername());
        session.setAttribute("role", roles);
        model.addAttribute("listCustomer", listCustomer);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listProduct", listProduct);

        model.addAttribute("listProductDetail", listProductDetail);

        model.addAttribute("selectedProduct", session.getAttribute("selectedProduct"));
        model.addAttribute("selectedSize", session.getAttribute("selectedSize"));
        model.addAttribute("selectedColor", session.getAttribute("selectedColor"));
        return "manager_sell/index";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(required = false) Integer searchProduct, @RequestParam(required = false) Integer searchSize, @RequestParam(required = false) Integer searchColor, Model model, HttpSession session) {

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

        session.setAttribute("listColor", listColor);
        session.setAttribute("listSize", listSize);
        session.setAttribute("listProduct", listProduct);
        session.setAttribute("listProductDetail", filteredProductDetails);

        session.setAttribute("selectedProduct", searchProduct);
        session.setAttribute("selectedSize", searchSize);
        session.setAttribute("selectedColor", searchColor);

        return "redirect:/sell-manager/index";
    }


    @GetMapping("/choose-customer/{id}")
    public String choseCustomer(@PathVariable("id") Integer id, HttpSession session) {
        session.removeAttribute("errorCustomer");
        Customer customer = customerRepository.findById(id).get();
        session.setAttribute("customerInfo", customer);
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/create-order")
    public String createOder(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customerInfo");
        String userNameStaff = (String) session.getAttribute("userName");
        Staff staff = staffRepository.findByUsername(userNameStaff);
        Customer customerChose = customerRepository.findById(customer.getId()).get();
        List<Bill> listBill = billRepository.findAll();
        if (customer == null) {
            session.setAttribute("errorCustomer", "Please choose customer");
            return "redirect:/sell-manager/index";
        }

        Bill bill = new Bill();
        bill.setId(listBill.size() + 1);
        bill.setStatus(0);
        bill.setStaff(staff);
        bill.setCustomer(customerChose);
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
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        List<Cart> cartItems = cartRepository.findAllByBill_Id(idOrder);
        List<Bill> billList = (List<Bill>) session.getAttribute("billList");
        if (idOrder != null) {
            if (cartItems != null) {
                for (Cart cart : cartItems) {
                    ProductDetail productDetail = productDetailRepository.findById(Long.valueOf(cart.getProductDetail().getId())).get();
                    productDetailRepository.updateQuantityClear(productDetail.getCode(), cart.getQuantity());
                    cartRepository.delete(cart);
                }
                billRepository.changeStatus(StatusOder.CANCEL.getValue());
                session.setAttribute("idOrderChose", null);
                billList.removeIf(bill -> bill.getId().equals(id));
            } else {
                billRepository.changeStatus(StatusOder.CANCEL.getValue());
                session.setAttribute("idOrderChose", null);
                billList.removeIf(bill -> bill.getId().equals(id));
            }
        }
        return "redirect:/sell-manager/index";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@ModelAttribute("customer") Customer customer, HttpSession session) {
        customerRepository.save(customer);
        return "redirect:/sell-manager/index";
    }


    @GetMapping("/choose-oder/{id}")
    public String chooseOder(@PathVariable("id") Integer id, HttpSession session) {
        Bill bill = billRepository.findById(Long.valueOf(id)).get();
        session.setAttribute("idOrderChose", bill.getId());
        session.setAttribute("bill", bill);
        return "redirect:/sell-manager/index";
    }

    @GetMapping("/show-model-product")
    public String showModelProduct(@RequestParam("id") Integer id, Model model) {
        ProductDetail productDetail = productDetailRepository.findById(Long.valueOf(id)).get();
        model.addAttribute("productDetail", productDetail);
        return "manager_sell/model";
    }


    @Transactional
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("code") String code, @RequestParam("quantity") Integer quantity, HttpSession session) {
        ProductDetail productDetail = productDetailRepository.findByCode(code);
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        List<Bill> billList = (List<Bill>) session.getAttribute("billList");
        Bill bill = billRepository.findById(Long.valueOf(idOrder)).get();
        boolean exitProductDetail = cartRepository.existsByBillIdAndProductDetail(idOrder, productDetail);
        boolean isExist = cartRepository.existsCartByBillId(idOrder);
        if (isExist) {
            if (exitProductDetail) {
                Cart cart = cartRepository.findByBillIdAndProductDetail(idOrder, productDetail);
                cart.setQuantity(cart.getQuantity() + quantity);
                cart.setPrice(cart.getPrice() + productDetail.getPrice() * quantity);
                productDetailRepository.updateQuantity(productDetail.getId(), quantity);
                cartRepository.save(cart);
                if (billList != null) {
                    for (Bill bill1 : billList) {
                        if (bill1.getId().equals(idOrder)) {
                            bill1.setTotal(bill1.getTotal() + productDetail.getPrice() * quantity);
                        }
                    }
                }
            } else {
                Cart cart = new Cart();
                cart.setBill(bill);
                cart.setProductDetail(productDetail);
                cart.setQuantity(quantity);
                cart.setPrice(productDetail.getPrice() * quantity);
                cart.setCustomer(customerRepository.findById(bill.getCustomer().getId()).get());
                productDetailRepository.updateQuantity(productDetail.getId(), quantity);
                cartRepository.save(cart);
                if (billList != null) {
                    for (Bill bill1 : billList) {
                        if (bill1.getId().equals(idOrder)) {
                            bill1.setTotal(bill1.getTotal() + productDetail.getPrice() * quantity);
                        }
                    }
                }
            }

        } else {
            Cart cart = new Cart();
            cart.setBill(bill);
            cart.setProductDetail(productDetail);
            cart.setQuantity(quantity);
            cart.setCustomer(customerRepository.findById(bill.getCustomer().getId()).get());
            productDetailRepository.updateQuantity(productDetail.getId(), quantity);
            cart.setPrice(productDetail.getPrice() * quantity);
            cartRepository.save(cart);
            if (billList != null) {
                for (Bill bill1 : billList) {
                    if (bill1.getId().equals(idOrder)) {
                        bill1.setTotal(bill1.getTotal() + productDetail.getPrice() * quantity);
                    }
                }
            }
        }
        return "redirect:/sell-manager/index";
    }


    @Transactional
    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable("id") Long id, HttpSession session) {
        Integer idOrderChose = (Integer) session.getAttribute("idOrderChose");
        Bill bill = billRepository.findById(Long.valueOf(idOrderChose)).get();
        Bill billTotal = (Bill) session.getAttribute("bill");
        if (idOrderChose != null) {
            Cart cart = cartRepository.findCartByBillId(idOrderChose);
            if (cart != null) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBill(billRepository.findById(Long.valueOf(idOrderChose)).get());
                billDetail.setProductDetail(cart.getProductDetail());
                billDetail.setQuantity(cart.getQuantity());
                billDetail.setPrice(cart.getPrice());
                billDetail.setStatus((int) StatusOder.SUCCESS.getValue());
                bill.setStatus((int) StatusOder.SUCCESS.getValue());
                bill.setTotal(billTotal.getTotal());
                billDetailRepository.save(billDetail);
                cartRepository.delete(cart);
            } else {
                log.warn("Cart with ID {} not found for cart deleting.", id);
            }
        }

        return "redirect:/sell-manager/index";
    }


    @Transactional
    @GetMapping("/clear-all")
    public String clearAll(HttpSession session) {
        Integer idOrderChose = (Integer) session.getAttribute("idOrderChose");
        List<Cart> cartItems = cartRepository.findAllByBill_Id(idOrderChose);
        if (idOrderChose != null) {
            if (cartItems != null) {
                for (Cart cart : cartItems) {
                    ProductDetail productDetail = productDetailRepository.findById(Long.valueOf(cart.getProductDetail().getId())).get();
                    productDetailRepository.updateQuantityClear(productDetail.getCode(), cart.getQuantity());
                    cartRepository.delete(cart);
                }
            } else {
                log.warn("Cart with ID {} not found for cart deleting.", idOrderChose);
            }
        }

        return "redirect:/sell-manager/index";
    }

    @PostMapping("/pay")
    public String pay(HttpSession session) {
        Integer idOrder = (Integer) session.getAttribute("idOrderChose");
        List<Bill> billList = (List<Bill>) session.getAttribute("billList");
        if (idOrder != null) {
            List<Cart> cartItems = cartRepository.findAllByBill_Id(idOrder);
            Bill bill = billRepository.findById(Long.valueOf(idOrder)).orElse(null);
            if (bill != null && cartItems != null) {
                for (Cart cart : cartItems) {
                    BillDetail billDetail = new BillDetail();
                    billDetail.setBill(bill);
                    billDetail.setProductDetail(cart.getProductDetail());
                    billDetail.setQuantity(cart.getQuantity());
                    billDetail.setPrice(cart.getPrice());
                    billDetail.setStatus((int) StatusOder.SUCCESS.getValue());
                    bill.setTotal(bill.getTotal() + cart.getPrice());
                    billDetailRepository.save(billDetail);
                    cartRepository.delete(cart);
                }
                bill.setStatus((int) StatusOder.SUCCESS.getValue());
                billRepository.save(bill);

                if (billList != null) {
                    for (Bill bill1 : billList) {
                        if (bill1.getId().equals(idOrder)) {
                            bill1.setStatus((int) StatusOder.SUCCESS.getValue());
                        }
                    }
                }

                bill.setTotal((Double) session.getAttribute("total"));
                bill.setDateBuy(session.getAttribute("dateBuy") != null ? (Date) session.getAttribute("dateBuy") : new Date());
                billRepository.save(bill);

                session.setAttribute("idOrderChose", null);
                session.setAttribute("bill", null);
                session.setAttribute("dateBuy", null);
                session.setAttribute("total", 0);
            }
        }
        return "redirect:/sell-manager/index";
    }


}
