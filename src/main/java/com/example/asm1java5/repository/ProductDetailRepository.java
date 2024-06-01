package com.example.asm1java5.repository;

import com.example.asm1java5.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    Page<ProductDetail> findAllByStatus(int status, Pageable pageable);

    List<ProductDetail> findAllByProductId(int id);

    List<ProductDetail> findAllBySizeId(int id);

    List<ProductDetail> findAllByColorId(int id);

    List<ProductDetail> findAllByProductIdAndSizeIdAndColorId(Integer idSize, Integer idColor, Integer product);

    List<ProductDetail> findAllByProductIdAndSizeId(int productId, int idSize);

    List<ProductDetail> findAllByProductIdAndColorId(int productId, int idColor);

    List<ProductDetail> findAllBySizeIdAndColorId(int size , int color);

    List<ProductDetail> findAllByStatus(int status);

    ProductDetail findByCode(String code);

    @Modifying
    @Query("UPDATE ProductDetail pd SET pd.quantity = pd.quantity - :quantity WHERE pd.id = :id AND pd.quantity >= :quantity")
    void updateQuantity(Integer id, Integer quantity);

    @Modifying
    @Transactional
    @Query("UPDATE ProductDetail pd SET pd.quantity = pd.quantity + :quantity WHERE pd.code = :code")
    void updateQuantityClear(String code, int quantity);

    Page<ProductDetail> findAllByProductIdAndSizeIdAndColorId(Integer productId, Integer sizeId, Integer colorId, Pageable pageable);
    Page<ProductDetail> findAllByProductIdAndSizeId(Integer productId, Integer sizeId, Pageable pageable);
    Page<ProductDetail> findAllByProductIdAndColorId(Integer productId, Integer colorId, Pageable pageable);
    Page<ProductDetail> findAllByProductId(Integer productId, Pageable pageable);
    Page<ProductDetail> findAllBySizeIdAndColorId(Integer sizeId, Integer colorId, Pageable pageable);
    Page<ProductDetail> findAllBySizeId(Integer sizeId, Pageable pageable);
    Page<ProductDetail> findAllByColorId(Integer colorId, Pageable pageable);
    Page<ProductDetail> findAll(Pageable pageable);
}
