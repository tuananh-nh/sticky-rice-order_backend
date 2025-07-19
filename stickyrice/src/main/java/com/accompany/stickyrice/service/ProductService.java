package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.request.EditProductDto;
import com.accompany.stickyrice.dto.response.ProductItemDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService extends BaseService <Product,Long>{
    List<ProductListItemDto> getProductListItem();
    PaginatedResponseDto<ProductListItemDto> getPaginatedProducts(int page, int size);
    ProductItemDto createProduct(com.accompany.stickyrice.dto.request.CreateProductDto dto);
    Product editProductByDto(Long id, EditProductDto dto);
    Optional<EditProductDto> getProductItem(Long productId);
    // ProductService.java
    void updateProduct(Long id, String productName, String slug, Double price, Long categoryId,
                       String description, Boolean isActive, MultipartFile imageFile);

}
