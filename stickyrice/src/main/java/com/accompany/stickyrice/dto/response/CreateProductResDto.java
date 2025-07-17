package com.accompany.stickyrice.dto.response;

public class CreateProductResDto {
    private Long productId;
    private String productName;
    private String slug;
    private String description;
    private Double price;
    private String productImage;
    private Boolean isActive;
    private Long categoryId;
    private String categoryName;

    public CreateProductResDto() {
    }

    public CreateProductResDto(String productImage, Long productId, String productName, String slug, String description, Double price, Boolean isActive, Long categoryId, String categoryName) {
        this.productImage = productImage;
        this.productId = productId;
        this.productName = productName;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
}
