package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Product;
import com.example.asm1java5.entity.ProductDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDetailRepository {
    private final List<ProductDetail> listProductDetail;

    public ProductDetailRepository() {
        listProductDetail = new ArrayList<>();
        listProductDetail.add(new ProductDetail(1, "PD01", 1, 1, 1, 1.0, 100000 , 1));
        listProductDetail.add(new ProductDetail(2, "PD02", 2, 2, 2, 2.0, 200000, 0));
    }

    public List<ProductDetail> findAll() {
        return listProductDetail;
    }

    public void save(ProductDetail productDetail) {
        productDetail.setId(this.listProductDetail.size() + 1);
        this.listProductDetail.add(productDetail);
    }

    public void deleteById(Integer id) {
        for (ProductDetail productDetail : listProductDetail) {
            if (productDetail.getId().equals(id)) {
                listProductDetail.remove(productDetail);
                break;
            }
        }
    }

    public void update(ProductDetail productDetail) {
        for (ProductDetail c : listProductDetail) {
            if (c.getId().equals(productDetail.getId())) {
                c.setCode(productDetail.getCode());
                c.setIdProduct(productDetail.getIdProduct());
                c.setIdColor(productDetail.getIdColor());
                c.setIdSize(productDetail.getIdSize());
                c.setPrice(productDetail.getPrice());
                c.setQuantity(productDetail.getQuantity());
                c.setStatus(productDetail.getStatus());
                break;
            }
        }
    }

    public ProductDetail findById(Integer id) {
        for (ProductDetail productDetail : listProductDetail) {
            if (productDetail.getId().equals(id)) {
                return productDetail;
            }
        }
        return null;
    }
}
