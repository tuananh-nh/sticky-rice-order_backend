package com.accompany.stickyrice.dto.request;

public class EditProductDto {
    private Long productId;
    private String productImage;
    private String productName;
    private String description;
    private Double price;
    private Boolean isActive;
    private String slug;
    private Long categoryId;
    private String categoryName;

    public EditProductDto() {
    }

    public EditProductDto(Long productId, String productImage, String productName, String description, Double price, Boolean isActive, String slug, Long categoryId, String categoryName) {
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.slug = slug;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
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
}
