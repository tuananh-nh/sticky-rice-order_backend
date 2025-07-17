package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.response.CategoryListItemOrderDto;
import com.accompany.stickyrice.dto.response.ProductListItemOrderDto;
import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CategoryListItemOrderDto toCategoryListItemOrderDto(ProductCategory productCategory) {
        if (productCategory == null) return null;

        Set<ProductListItemOrderDto> productDtos = productCategory.getProducts()
                .stream()
                .map(productMapper::toProductListItemOrderDto)
                .collect(Collectors.toSet());

        return new CategoryListItemOrderDto(
                productCategory.getCategoryName(),
                productCategory.getSlug(),
                productDtos
        );
    }
}
