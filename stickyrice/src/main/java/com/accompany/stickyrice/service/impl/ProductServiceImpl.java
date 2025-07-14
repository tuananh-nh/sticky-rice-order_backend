package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product dto) {
        return null;
    }

    @Override
    public Product update(Long aLong, Product dto) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Boolean existsById(Long aLong) {
        return null;
    }


}
