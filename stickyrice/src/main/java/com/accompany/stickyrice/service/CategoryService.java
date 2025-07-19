package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.request.EditCategoryDto;
import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.dto.response.CategoryListItemDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.entity.ProductCategory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface  CategoryService extends BaseService<ProductCategory, Long> {

    List<CategorySummaryDto> getAllCategoriesWithProducts();

    Optional<CategorySummaryDto> getCateWithProductsBySlug(String slug);

    List<CategoryListItemDto> getAllCategoryListItems();

    EditCategoryDto createCategory(EditCategoryDto request);

    EditCategoryDto updateCategory(Long id, EditCategoryDto dto);

    EditCategoryDto getById(Long id);

    void deleteById(Long id);

    PaginatedResponseDto<CategoryListItemDto> getPaginatedProductsCategory(int page, int size);

    List<CategoryListItemDto> getCategoryListItemDtos();
}
