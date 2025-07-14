package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("""
        SELECT DISTINCT c
        FROM ProductCategory c
        LEFT JOIN FETCH c.products p
        LEFT JOIN FETCH p.voucher
    """)
    List<ProductCategory> getAllCategoriesWithProducts();
}
