package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.response.ProductSummaryDto;
import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.entity.Voucher;

import java.util.Optional;

public class ProductSummaryMapper {
    public static ProductSummaryDto toDto(Product product) {
        Voucher voucher = product.getVoucher();
        Double discountedPrice = Optional.ofNullable(product.getVoucher())
                .map(Voucher::getDiscountPercent)
                .map(dp -> product.getPrice() * (1 - dp)) // hoặc chia 100 nếu cần
                .orElse(null);

        return new ProductSummaryDto(
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
