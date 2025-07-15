package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;

public class ProductListItemMapper {
    public static ProductListItemDto toDto (Product product) {
        return new ProductListItemDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductImage(),
                product.getPrice(),
                product.getSlug(),
                product.getIsActive(),
                product.getProductCategory().getCategoryId(),
                product.getProductCategory().getCategoryName()
        );
    }
}
