package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.request.CreateProductDto;
import com.accompany.stickyrice.dto.response.ProductItemDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.dto.response.ProductListItemOrderDto;
import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.entity.ProductCategory;
import com.accompany.stickyrice.entity.Voucher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductMapper {

    // 🟢 Map từ DTO tạo mới → Entity
    public Product toEntity(CreateProductDto dto, ProductCategory category) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setSlug(dto.getSlug());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setIsActive(dto.getIsActive());
        product.setProductImage(dto.getProductImage());
        product.setProductCategory(category);
        return product;
    }

    // 🔵 Map từ Entity → DTO chi tiết sản phẩm (chuẩn hóa lại dùng ProductResponseDto)
    public ProductItemDto toProductItemDto(Product product) {
        ProductItemDto dto = new ProductItemDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setSlug(product.getSlug());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setProductImage(product.getProductImage());
        dto.setIsActive(product.getIsActive());
        dto.setCategoryId(product.getProductCategory().getCategoryId());
        dto.setCategoryName(product.getProductCategory().getCategoryName());
        return dto;
    }

    // 🟡 Map từ Entity → DTO cho danh sách (list view)
    public ProductListItemDto toProductListItemDto(Product product) {
        return new ProductListItemDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductImage(),
                product.getPrice(),
                product.getSlug(),
                product.getIsActive(),
                product.getProductCategory().getCategoryId(),
                product.getProductCategory().getCategoryName()
        );
    }

    // 🟣 Map từ Entity → DTO tóm tắt sản phẩm + tính giá giảm nếu có
    public ProductListItemOrderDto toProductListItemOrderDto(Product product) {
        Double discountedPrice = Optional.ofNullable(product.getVoucher())
                .map(Voucher::getDiscountPercent)
                .map(discount -> product.getPrice() * (1 - discount))
                .orElse(null);

        return new ProductListItemOrderDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductImage(),
                product.getDescription(),
                product.getPrice(),
                product.getSlug(),
                discountedPrice
        );
    }
}
