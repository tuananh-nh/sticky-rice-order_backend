package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.request.EditCategoryDto;
import com.accompany.stickyrice.dto.response.CategoryListItemDto;
import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Danh mục kèm sản phẩm (cho người dùng)
    @GetMapping("order/products")
    public ResponseEntity<List<CategorySummaryDto>> getCategoriesWithProducts() {
        return ResponseEntity.ok(categoryService.getAllCategoriesWithProducts());
    }

    // Danh mục theo slug (cho người dùng)
    @GetMapping("order/products/{slug}")
    public ResponseEntity<CategorySummaryDto> getCateWithProductsBySlug(@PathVariable String slug) {
        return categoryService.getCateWithProductsBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Danh mục đơn giản – chỉ id, tên, slug (cho admin)
    @GetMapping("admin/categories")
    public ResponseEntity<List<CategoryListItemDto>> getSimpleCategoryList() {
        return ResponseEntity.ok(categoryService.getAllCategoryListItems());
    }

    // Thêm danh mục sản phẩm
    @PostMapping("admin/categories")
    public ResponseEntity<EditCategoryDto> createCategory(@RequestBody EditCategoryDto request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("admin/categories/{id}")
    public ResponseEntity<EditCategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody EditCategoryDto dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    @GetMapping("admin/categories/{id}")
    public ResponseEntity<EditCategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }


    @DeleteMapping("admin/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    // API phân trang danh sách sản phẩm
    @GetMapping("admin/category")
    public ResponseEntity<PaginatedResponseDto<CategoryListItemDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        PaginatedResponseDto<CategoryListItemDto> result = categoryService.getPaginatedProductsCategory(page, size);
        return ResponseEntity.ok(result);
    }


}
