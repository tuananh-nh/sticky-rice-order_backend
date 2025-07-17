package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.request.CreateProductDto;
import com.accompany.stickyrice.dto.response.CreateProductResDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
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
            Path uploadPath = Paths.get(UPLOAD_DIR);
            System.out.println("Upload path absolute: " + uploadPath.toAbsolutePath());

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
            e.printStackTrace(); // In lỗi chi tiết ra console
            return ResponseEntity.status(500).body("Lỗi upload ảnh: " + e.getMessage());
        }
    }

    // ✅ API tạo sản phẩm (chỉ nhận JSON - không kèm file)
    @PostMapping
    public ResponseEntity<CreateProductResDto> createProduct(@RequestBody CreateProductDto dto) {
        CreateProductResDto response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
