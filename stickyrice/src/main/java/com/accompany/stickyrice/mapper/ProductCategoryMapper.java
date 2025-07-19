package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.request.EditCategoryDto;
import com.accompany.stickyrice.dto.response.CategoryListItemDto;
import com.accompany.stickyrice.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {

    // Dùng khi load danh sách (cho admin)
    public CategoryListItemDto toCategoryListItemDto(ProductCategory category) {
        return new CategoryListItemDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getSlug()
        );
    }

    // Dùng khi load dữ liệu để chỉnh sửa (getById)
    public EditCategoryDto toEditCategoryDto(ProductCategory entity) {
        return new EditCategoryDto(
                entity.getCategoryId(),
                entity.getCategoryName(),
                entity.getSlug()
        );
    }

    // Dùng khi cập nhật từ DTO vào entity (update)
    public void toEntity(ProductCategory entity, EditCategoryDto dto) {
        entity.setCategoryName(dto.getCategoryName());
        entity.setSlug(dto.getSlug());
    }
}
