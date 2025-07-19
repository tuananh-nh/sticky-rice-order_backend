package com.accompany.stickyrice.dto.request;

public class EditCategoryDto {
    private Long categoryId;
    private String categoryName;
    private String slug;

    public EditCategoryDto() {}

    public EditCategoryDto(Long categoryId, String categoryName, String slug) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.slug = slug;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
