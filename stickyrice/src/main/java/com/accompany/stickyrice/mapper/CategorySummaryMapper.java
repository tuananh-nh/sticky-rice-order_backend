package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.dto.response.ProductSummaryDto;
import com.accompany.stickyrice.entity.ProductCategory;

import java.util.Set;
import java.util.stream.Collectors;

public class CategorySummaryMapper {
    public CategorySummaryDto toDto(ProductCategory productCategory) {
        if (productCategory == null) return null;

        Set<ProductSummaryDto> productDtos = productCategory.getProducts()
                .stream()
                .map(ProductSummaryMapper::toDto)
                .collect(Collectors.toSet());

        return new CategorySummaryDto(
                productCategory.getCategoryName(),
                productCategory.getSlug(),
                productDtos
        );
    }
}
