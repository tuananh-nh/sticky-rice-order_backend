package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.CategoryListItemOrderDto;
import com.accompany.stickyrice.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends BaseService<ProductCategory, Long>{
    List<CategoryListItemOrderDto> getAllCategoriesWithProducts();
    Optional<CategoryListItemOrderDto> getCateWithProductsBySlug(String slug);
}
