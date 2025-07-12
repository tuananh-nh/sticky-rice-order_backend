package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.mapper.CategorySummaryMapper;
import com.accompany.stickyrice.repository.CategoryRepository;
import com.accompany.stickyrice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategorySummaryMapper categorySummaryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategorySummaryMapper categorySummaryMapper) {
        this.categoryRepository = categoryRepository;
        this.categorySummaryMapper = categorySummaryMapper;
    }

    @Override
    public CategorySummaryDto create(CategorySummaryDto dto) {
        return null;
    }

    @Override
    public CategorySummaryDto update(Long aLong, CategorySummaryDto dto) {
        return null;
    }

    @Override
    public CategorySummaryDto findById(Long aLong) {
        return null;
    }

    @Override
    public List<CategorySummaryDto> findAll() {
        return null;
    }

    @Override
    public List<CategorySummaryDto> getCategoriesProducts() {
        return categoryRepository.findAll()
                .stream()
                .map(categorySummaryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Boolean exitById(Long aLong) {
        return null;
    }
}
