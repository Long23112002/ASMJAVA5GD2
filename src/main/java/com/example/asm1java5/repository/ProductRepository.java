package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ProductRepository {
    private final List<Product> listProduct;

    public ProductRepository() {
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "PD01", "Quần", 1));
        listProduct.add(new Product(2, "PD02", "Áo", 1));
    }

    public List<Product> findAll() {
        return listProduct;
    }

    public List<Product> findAllByStatusActive() {
        return listProduct.stream()
                .filter(product -> product.getStatus() == 1)
                .collect(Collectors.toList());
    }

    public Page<Product> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listProduct.size());
        List<Product> pageContent = listProduct.subList(startIndex, endIndex);
        log.info("Page content: {}", pageContent);
        log.info("PageSize : {}", pageSize);
        log.info("PageNumber : {}", pageNumber);
        log.info("StartIndex : {}", startIndex);
        log.info("EndIndex : {}", endIndex);
        return new PageImpl<>(pageContent, pageable, listProduct.size());
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
