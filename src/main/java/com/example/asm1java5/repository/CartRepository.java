package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Cart;
import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.ProductDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    private final List<Cart> listCart = new ArrayList<>();

    public List<Cart> findAll() {
        return listCart;
    }

    public void save(Cart cart) {
        cart.setId(listCart.size() + 1);
        listCart.add(cart);
    }

    public List<Cart> findAllByIdOder(int idOder) {
       return listCart.stream().filter(cart -> cart.getIdOder() == idOder).toList();
    }

    public Cart findByOrderIdAndProductDetailId(int orderId, int productDetailId) {
        for (Cart cart : listCart) {
            if (cart.getIdOder() == orderId) {
                for (ProductDetail productDetail : cart.getProductDetailList()) {
                    if (productDetail.getId() == productDetailId) {
                        return cart;
                    }
                }
            }
        }
        return null;
    }

    public void update(Cart cart) {
        for (Cart c : listCart) {
            if (c.getId().equals(cart.getId())) {
                c.setTotal(cart.getTotal());
                c.setPrice(cart.getPrice());
                break;
            }
        }
    }

    public void clearAllCardByOrderId(int orderId) {
        listCart.removeIf(cart -> cart.getIdOder() == orderId);
    }

    public void deleteById(Integer id) {
        listCart.removeIf(cart -> cart.getId().equals(id));
    }

    public Cart findById(Integer id) {
        for (Cart cart : listCart) {
            if (cart.getId().equals(id)) {
                return cart;
            }
        }
        return null;
    }




}
