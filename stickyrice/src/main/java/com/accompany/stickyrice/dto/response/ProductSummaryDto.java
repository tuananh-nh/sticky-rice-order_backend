package com.accompany.stickyrice.dto.response;

import java.util.UUID;

public class ProductSummaryDto {
    private Long productId;
    private String productName;
    private String productImage;
    private String description;
    private Double price;
    private String slug;
    private Double discount;

    public ProductSummaryDto() {
    }

    public ProductSummaryDto(Long productId, String productName, String productImage, String description, Double price, String slug, Double discount) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
        this.slug = slug;
        this.discount = discount;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
