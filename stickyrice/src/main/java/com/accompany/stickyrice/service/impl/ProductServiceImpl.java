package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.mapper.ProductListItemMapper;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Override
    public List<ProductListItemDto> getProductListItem() {
        Pageable pageable = PageRequest.of(0, 5);
        return productRepository.findAllProducts(pageable)
                .stream()
                .map(ProductListItemMapper::toDto)
                .toList();
    }

    @Override
    public Map<String, Object> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAllProducts(pageable);

        // Chuyển danh sách Product sang DTO
        List<ProductListItemDto> dtos = productPage.getContent()
                .stream()
                .map(ProductListItemMapper::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", dtos);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());

        return response;
    }

    }
