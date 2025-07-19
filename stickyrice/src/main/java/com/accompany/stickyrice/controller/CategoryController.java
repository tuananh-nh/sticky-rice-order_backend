package com.accompany.stickyrice.controller;

import com.accompany.stickyrice.dto.response.CategoryListItemOrderDto;
import com.accompany.stickyrice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("order/products")
    public ResponseEntity<List<CategoryListItemOrderDto>> getCategoriesProducts(){
        return ResponseEntity.ok(categoryService.getAllCategoriesWithProducts());
    }

    @GetMapping("order/products/{slug}")
    public ResponseEntity<CategoryListItemOrderDto> getCateWithProductsBySlug(@PathVariable String slug){
        return categoryService.getCateWithProductsBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
