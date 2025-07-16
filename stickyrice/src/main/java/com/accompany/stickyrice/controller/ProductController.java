package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * ✅ API phân trang danh sách sản phẩm
     */
    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Map<String, Object> result = productService.getPaginatedProducts(page, size);
        return ResponseEntity.ok(result);
    }
}
