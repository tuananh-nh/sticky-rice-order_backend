package com.accompany.stickyrice.mapper;

import com.accompany.stickyrice.dto.ProductDTO;
import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.entity.ProductCategory;
import com.accompany.stickyrice.entity.Voucher;

public class ProductMapper {

    // Dùng static vì không cần trạng thái và tiện gọi qua ProductMapper.toDTO(...)
    public static ProductDTO toDTO(Product p) {
        ProductCategory category = p.getProductCategory();
        Voucher voucher = p.getVoucher();

        return new ProductDTO(
                p.getProductId(),
                p.getProductName(),
                p.getProductImage(),
                p.getDescription(),
                p.getPrice(),
                p.getIsActive(),
                p.getSlug(),
                category != null ? category.getCategoryId() : null,
                category != null ? category.getCategoryName() : null,
                voucher != null ? voucher.getVoucherId() : null
        );
    }
}
