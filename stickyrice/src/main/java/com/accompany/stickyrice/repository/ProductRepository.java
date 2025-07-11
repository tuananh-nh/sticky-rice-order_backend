package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
