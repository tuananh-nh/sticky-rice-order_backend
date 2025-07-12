package com.accompany.stickyrice.dto.response;


import java.util.Set;

public class CategorySummaryDto {
    private String categoryName;
    private String slug;
    Set<ProductSummaryDto> productSummaryDtos;

    public CategorySummaryDto() {
    }

    public CategorySummaryDto(String categoryName, String slug, Set<ProductSummaryDto> productSummaryDtos) {
        this.categoryName = categoryName;
        this.slug = slug;
        this.productSummaryDtos = productSummaryDtos;
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

    public Set<ProductSummaryDto> getProductSummaryDtos() {
        return productSummaryDtos;
    }

    public void setProductSummaryDtos(Set<ProductSummaryDto> productSummaryDtos) {
        this.productSummaryDtos = productSummaryDtos;
    }
}
