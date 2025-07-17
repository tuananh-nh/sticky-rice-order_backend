package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.dto.response.ProductSummaryDto;
import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategorySummaryMapper {

    private final ProductMapper productMapper;

    public CategorySummaryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CategorySummaryDto toDto(ProductCategory productCategory) {
        if (productCategory == null) return null;

        Set<ProductSummaryDto> productDtos = productCategory.getProducts()
                .stream()
                .map(productMapper::toSummaryDto)
                .collect(Collectors.toSet());

        return new CategorySummaryDto(
                productCategory.getCategoryName(),
                productCategory.getSlug(),
                productDtos
        );
    }
}
