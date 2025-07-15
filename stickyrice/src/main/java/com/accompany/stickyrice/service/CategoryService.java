package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends BaseService<ProductCategory, Long>{
    List<CategorySummaryDto> getAllCategoriesWithProducts();
    Optional<CategorySummaryDto> getCateWithProductsBySlug(String slug);
}
