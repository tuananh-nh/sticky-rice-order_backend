package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.request.CreateProductDto;
import com.accompany.stickyrice.dto.request.EditProductDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.dto.response.ProductItemDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.entity.Product;
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

    // ✅ Phân trang danh sách sản phẩm
    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ProductListItemDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        PaginatedResponseDto<ProductListItemDto> result = productService.getPaginatedProducts(page, size);
        return ResponseEntity.ok(result);
    }

    // ✅ Upload ảnh
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExt = "";
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                fileExt = originalFilename.substring(dotIndex);
            }
            String newFileName = UUID.randomUUID() + fileExt;

            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi upload ảnh: " + e.getMessage());
        }
    }

    // ✅ Tạo sản phẩm
    @PostMapping
    public ResponseEntity<ProductItemDto> createProduct(@RequestBody CreateProductDto dto) {
        ProductItemDto response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<EditProductDto> getProductById(@PathVariable Long id) {
        Optional<EditProductDto> editProductDto = productService.getProductItem(id);

        return editProductDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    // ✅ Cập nhật sản phẩm
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody EditProductDto dto) {
//        try {
//            Product updated = productService.editProductByDto(id, dto);
//            return ResponseEntity.ok("Cập nhật sản phẩm thành công");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Lỗi khi cập nhật sản phẩm");
//        }
//    }

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



    // ✅ Xóa sản phẩm
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
