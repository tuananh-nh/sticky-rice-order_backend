package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("""
        SELECT DISTINCT c
        FROM ProductCategory c
        LEFT JOIN FETCH c.products p
        LEFT JOIN FETCH p.voucher
    """)
    List<ProductCategory> getAllCategoriesWithProducts();

    @Query("""
            SELECT DISTINCT c
            FROM ProductCategory c
            LEFT JOIN FETCH c.products p
            LEFT JOIN FETCH p.voucher
            WHERE c.slug = :slug
            """)
    Optional<ProductCategory> getCateWithProductsBySlug(@Param("slug") String slug);

    // Thêm dòng này để fix lỗi existsBySlug
    boolean existsBySlug(String slug);
    boolean existsByCategoryNameIgnoreCase(String categoryName);

    @Query("SELECT c FROM ProductCategory c")
    Page<ProductCategory> findAllProductCategory(Pageable pageable);

}
