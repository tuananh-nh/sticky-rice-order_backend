package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.ProductItemDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;

import java.util.List;

public interface ProductService extends BaseService <Product,Long>{
    List<ProductListItemDto> getProductListItem();
    PaginatedResponseDto<ProductListItemDto> getPaginatedProducts(int page, int size);
    ProductItemDto createProduct(com.accompany.stickyrice.dto.request.CreateProductDto dto);
}
