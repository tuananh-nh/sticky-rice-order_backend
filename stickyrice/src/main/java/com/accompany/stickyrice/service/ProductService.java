package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.request.CreateProductDto;
import com.accompany.stickyrice.dto.response.CreateProductResDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService extends BaseService <Product,Long>{
    List<ProductListItemDto> getProductListItem();
    PaginatedResponseDto<ProductListItemDto> getPaginatedProducts(int page, int size);
    CreateProductResDto createProduct(CreateProductDto dto);
}
