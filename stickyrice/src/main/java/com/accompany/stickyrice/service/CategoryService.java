package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.CategorySummaryDto;

import java.util.List;

public interface CategoryService extends BaseService<CategorySummaryDto, Long>{
    List<CategorySummaryDto> getCategoriesProducts();
}
