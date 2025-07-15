package com.accompany.stickyrice.service;

import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;

import java.util.List;

public interface ProductService extends BaseService <Product, Long>{
    List<ProductListItemDto> getProductListItem();
}
