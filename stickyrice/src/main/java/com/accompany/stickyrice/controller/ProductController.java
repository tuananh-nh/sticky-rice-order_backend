package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.request.EditProductDto;
import com.accompany.stickyrice.dto.response.ProductItemDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/images/";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ API phân trang danh sách sản phẩm
    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ProductListItemDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        PaginatedResponseDto<ProductListItemDto> result = productService.getPaginatedProducts(page, size);
        return ResponseEntity.ok(result);
    }

    // ✅ API upload ảnh riêng
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = productService.uploadImage(file);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi upload ảnh: " + e.getMessage());
        }
    }

    // ✅ API tạo sản phẩm (chỉ nhận JSON - không kèm file)
    @PostMapping
    public ResponseEntity<ProductItemDto> createProduct(@RequestBody com.accompany.stickyrice.dto.request.CreateProductDto dto) {
        ProductItemDto response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditProductDto> getProductById(@PathVariable Long id) {
        Optional<EditProductDto> editProductDto = productService.getProductItem(id);

        return editProductDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestParam("productName") String productName,
            @RequestParam("slug") String slug,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("description") String description,
            @RequestParam("isActive") Boolean isActive,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            productService.updateProduct(id, productName, slug, price, categoryId, description, isActive, imageFile);
            return ResponseEntity.ok("Cập nhật sản phẩm thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật thất bại: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.ok("Xóa sản phẩm thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi xóa sản phẩm");
        }
    }

}
