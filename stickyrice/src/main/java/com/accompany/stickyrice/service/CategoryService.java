package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.entity.ProductCategory;

import java.util.List;

public interface CategoryService extends BaseService<ProductCategory, Long>{
    List<CategorySummaryDto> getAllCategoriesWithProducts();
}
