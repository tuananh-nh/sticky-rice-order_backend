package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

}
