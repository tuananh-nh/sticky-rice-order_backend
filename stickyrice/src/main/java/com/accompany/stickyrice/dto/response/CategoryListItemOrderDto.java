package com.accompany.stickyrice.dto.response;


import java.util.Set;

public class CategoryListItemOrderDto {
    private String categoryName;
    private String slug;
    Set<ProductListItemOrderDto> productListItemOrderDtos;

    public CategoryListItemOrderDto() {
    }

    public CategoryListItemOrderDto(String categoryName, String slug, Set<ProductListItemOrderDto> productListItemOrderDtos) {
        this.categoryName = categoryName;
        this.slug = slug;
        this.productListItemOrderDtos = productListItemOrderDtos;
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

    public Set<ProductListItemOrderDto> getProductSummaryDtos() {
        return productListItemOrderDtos;
    }

    public void setProductSummaryDtos(Set<ProductListItemOrderDto> productListItemOrderDtos) {
        this.productListItemOrderDtos = productListItemOrderDtos;
    }
}
