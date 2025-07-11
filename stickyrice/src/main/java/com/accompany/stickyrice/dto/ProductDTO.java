package com.accompany.stickyrice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDTO {
    private Long productId;
    private String productName;
    private String productImage;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private String slug;
    private Long categoryId;
    private String categoryName;
    private UUID voucherId;

    // Constructor mặc định
    public ProductDTO() {
    }

    // Constructor đầy đủ tham số
    public ProductDTO(Long productId, String productName, String productImage,
                      String description, BigDecimal price, Boolean isActive,
                      String slug, Long categoryId, String categoryName, UUID voucherId) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.slug = slug;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.voucherId = voucherId;
    }

    // Getter và Setter
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
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

    public UUID getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(UUID voucherId) {
        this.voucherId = voucherId;
    }
}
