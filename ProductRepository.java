package com.basilisk.dao;

import com.basilisk.dto.product.ProductGridDTO;
import com.basilisk.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
            SELECT COUNT(pro.id)
            FROM Product AS pro
            WHERE pro.categoryId = :categoryId
            """)
    public Long countByCategoryId(@Param("categoryId") Long categoryId);

    @Query("""
            SELECT new com.basilisk.dto.product.ProductGridDTO(pro.id, pro.name, sup.companyName, cat.name, pro.price)
            FROM Product AS pro
                LEFT JOIN pro.supplier AS sup
                INNER JOIN pro.category AS cat
            WHERE pro.name LIKE %:productName%
            """)
    public List<ProductGridDTO> findAll(@Param("productName") String productName, Pageable pageable);

    @Query("""
            SELECT COUNT(pro.id)
            FROM Product AS pro
            WHERE pro.name LIKE %:productName%
            """)
    public Long count(@Param("productName") String productName);

    @Query("""
            SELECT new com.basilisk.dto.product.ProductGridDTO(pro.id, pro.name, sup.companyName, cat.name, pro.price)
            FROM Product AS pro
                LEFT JOIN pro.supplier AS sup
                INNER JOIN pro.category AS cat
            WHERE pro.categoryId = :categoryId
            """)
    public List<ProductGridDTO> findAll(@Param("categoryId") Long categoryId,Pageable pageable );

    @Query("""
            SELECT COUNT(pro.id)
            FROM Product AS pro
            WHERE pro.categoryId = :categoryId
            """)
    public Long count(@Param("categoryId") Long categoryId);
}
