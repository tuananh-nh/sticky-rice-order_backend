package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.ProductDTO;
import com.accompany.stickyrice.mapper.ProductMapper;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.BaseService;
import com.accompany.stickyrice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        return null;
    }

    @Override
    public ProductDTO update(Long aLong, ProductDTO dto) {
        return null;
    }

    @Override
    public ProductDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<ProductDTO> findAll() {

        return List.of();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Boolean exitById(Long aLong) {
        return null;
    }
}
