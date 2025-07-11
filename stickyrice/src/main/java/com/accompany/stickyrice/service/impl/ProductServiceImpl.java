package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.ProductDTO;
import com.accompany.stickyrice.mapper.ProductMapper;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
