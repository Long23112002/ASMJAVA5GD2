package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Cart;
import com.example.asm1java5.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsCartByBillId(Integer bill_id);

    @Query("SELECT COUNT(c) > 0 FROM Cart c WHERE c.bill.id = :billId AND c.productDetail = :productDetail")
    boolean existsByBillIdAndProductDetail(Integer billId, ProductDetail productDetail);

    Cart findByBillIdAndProductDetail(Integer billId, ProductDetail productDetail);

    List<Cart> findAllByBill_Id(Integer billId);

    Cart findCartByBillId(int billId);


}
