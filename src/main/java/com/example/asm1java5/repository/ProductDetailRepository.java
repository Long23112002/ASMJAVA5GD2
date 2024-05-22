package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Product;
import com.example.asm1java5.entity.ProductDetail;
import com.example.asm1java5.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductDetail> findAllByStatusActive(){
        return listProductDetail.stream()
                .filter(productDetail -> productDetail.getStatus() == 1)
                .collect(Collectors.toList());
    }

    public Page<ProductDetail> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listProductDetail.size());
        List<ProductDetail> pageContent = listProductDetail.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listProductDetail.size());
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
