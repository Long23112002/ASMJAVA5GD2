package com.example.asm1java5.service.impl;

import com.example.asm1java5.dto.response.CartResponse;
import com.example.asm1java5.entity.Cart;
import com.example.asm1java5.entity.ProductDetail;
import com.example.asm1java5.repository.CartRepository;
import com.example.asm1java5.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService {


    private CartRepository cartRepository;
    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

//    @Override
//    public List<CartResponse> getAllCartByBillId(int billId) {
//        List<Cart> listCart = cartRepository.findAllByBill_Id(billId);
//        List<CartResponse> listCartResponse = new ArrayList<>();
//        for (Cart cart : listCart) {
//            CartResponse cartResponse = new CartResponse();
//            cartResponse.setIdCard(cart.getId());
//            cartResponse.setQuantity(cart.getQuantity());
//            List<ProductDetail> productDetailList = cart.getProductDetailList();
//            for (ProductDetail productDetail : productDetailList) {
//                cartResponse.setCodeProduct(productDetail.getCode());
//                cartResponse.setNameProduct(productDetail.getProduct().getName());
//                cartResponse.setNameColor(productDetail.getColor().getName());
//                cartResponse.setNameSize(productDetail.getSize().getName());
//                cartResponse.setPrice(productDetail.getPrice());
//                cartResponse.setTotal(productDetail.getPrice() * cart.getQuantity());
//            }
//            listCartResponse.add(cartResponse);
//        }
//        return listCartResponse;
//    }

}
