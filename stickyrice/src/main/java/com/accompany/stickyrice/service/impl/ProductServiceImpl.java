package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.request.CreateProductDto;
import com.accompany.stickyrice.dto.response.CreateProductResDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.entity.ProductCategory;
import com.accompany.stickyrice.mapper.ProductMapper;
import com.accompany.stickyrice.repository.CategoryRepository;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
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

    // ✅ Lấy top 5 sản phẩm đầu tiên
    @Override
    public List<ProductListItemDto> getProductListItem() {
        Pageable pageable = PageRequest.of(0, 5);
        return productRepository.findAllProducts(pageable)
                .stream()
                .map(productMapper::toListItemDto)
                .toList();
    }

    // ✅ Sử dụng PaginatedResponseDto thay vì Map
    @Override
    public PaginatedResponseDto<ProductListItemDto> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAllProducts(pageable);

        List<ProductListItemDto> dtos = productPage.getContent()
                .stream()
                .map(productMapper::toListItemDto)
                .toList();

        return new PaginatedResponseDto<>(
                dtos,
                productPage.getNumber(),
                productPage.getTotalPages(),
                productPage.getTotalElements()
        );
    }

    // ✅ Tạo sản phẩm mới — đã loại bỏ voucher
    @Override
    public CreateProductResDto createProduct(CreateProductDto dto) {
        ProductCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục sản phẩm"));

        Product product = productMapper.toEntity(dto, category);
        productRepository.save(product);

        return productMapper.toDto(product);
    }
}
