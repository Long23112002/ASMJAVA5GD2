package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> listProduct;

    public ProductRepository() {
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "PD01", "Quan", 1));
        listProduct.add(new Product(2, "PD02", "Ao", 0));
    }

    public List<Product> findAll() {
        return listProduct;
    }

    public void save(Product product) {
        product.setId(this.listProduct.size() + 1);
        this.listProduct.add(product);
    }

    public void deleteById(Integer id) {
        for (Product product : listProduct) {
            if (product.getId().equals(id)) {
                listProduct.remove(product);
                break;
            }
        }
    }

    public void update(Product product) {
        for (Product c : listProduct) {
            if (c.getId().equals(product.getId())) {
                c.setCode(product.getCode());
                c.setName(product.getName());
                c.setStatus(product.getStatus());
                break;
            }
        }
    }

    public Product findById(Integer id) {
        for (Product product : listProduct) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
