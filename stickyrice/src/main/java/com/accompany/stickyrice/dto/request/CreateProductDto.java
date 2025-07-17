package com.accompany.stickyrice.dto.request;

public class CreateProductDto {
    private String productName;
    private String slug;
    private String description;
    private Double price;
    private Boolean isActive;
    private Long categoryId;
    private String productImage;

    public CreateProductDto(String productImage, Long categoryId, Boolean isActive,
                            Double price, String description, String slug, String productName) {
        this.productImage = productImage;
        this.categoryId = categoryId;
        this.isActive = isActive;
        this.price = price;
        this.description = description;
        this.slug = slug;
        this.productName = productName;
    }

    public CreateProductDto() {
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
