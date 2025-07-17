package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.response.CategoryListItemOrderDto;
import com.accompany.stickyrice.entity.ProductCategory;
import com.accompany.stickyrice.entity.Voucher;
import com.accompany.stickyrice.mapper.CategoryMapper;
import com.accompany.stickyrice.repository.CategoryRepository;
import com.accompany.stickyrice.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public List<CategoryListItemOrderDto> getAllCategoriesWithProducts() {

        List<ProductCategory> categories = categoryRepository.getAllCategoriesWithProducts();
        LocalDateTime nowadays = LocalDateTime.now();

        categories.forEach(cat ->
                cat.getProducts().forEach(
                        prod -> {
                            Voucher voucher = prod.getVoucher();

                            boolean invalid =
                                    voucher == null ||
                                    voucher.getDiscountPercent() == null ||
                                    voucher.getDateEnd() == null ||
                                    voucher.getDateEnd().isBefore(nowadays);

                            if (invalid && voucher != null) {
                                prod.setVoucher(null);
                                voucher.setStatus(false);
                            }
                        }
                ));

        categoryRepository.saveAll(categories);
        return categories.stream()
                         .map(categoryMapper::toCategoryListItemOrderDto)
                         .toList();
    }

    @Override
    public Optional<CategoryListItemOrderDto> getCateWithProductsBySlug(String slug) {
        return categoryRepository.getCateWithProductsBySlug(slug)
                .map(categoryMapper::toCategoryListItemOrderDto);
    }

    @Override
    public ProductCategory create(ProductCategory dto) {
        return null;
    }

    @Override
    public ProductCategory update(Long aLong, ProductCategory dto) {
        return null;
    }

    @Override
    public Optional<ProductCategory> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<ProductCategory> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Boolean existsById(Long aLong) {
        return null;
    }
}
