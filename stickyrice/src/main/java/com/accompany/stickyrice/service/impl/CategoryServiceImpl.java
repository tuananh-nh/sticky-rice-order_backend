package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.request.EditCategoryDto;
import com.accompany.stickyrice.dto.response.CategoryListItemDto;
import com.accompany.stickyrice.dto.response.CategorySummaryDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.entity.ProductCategory;
import com.accompany.stickyrice.entity.Voucher;
import com.accompany.stickyrice.exception.BadRequestException;
import com.accompany.stickyrice.exception.ResourceConflictException;
import com.accompany.stickyrice.exception.ResourceNotFoundException;
import com.accompany.stickyrice.mapper.CategorySummaryMapper;
import com.accompany.stickyrice.mapper.ProductCategoryMapper;
import com.accompany.stickyrice.repository.CategoryRepository;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    //    private final ProductRepository productRepository;
    private final CategorySummaryMapper categorySummaryMapper;
    private final ProductCategoryMapper productCategoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository,
                               CategorySummaryMapper categorySummaryMapper,
                               ProductCategoryMapper productCategoryMapper) {
        this.categoryRepository = categoryRepository;
//        this.productRepository = productRepository;
        this.categorySummaryMapper = categorySummaryMapper;
        this.productCategoryMapper = productCategoryMapper;
    }

    // Tạo danh mục sản phẩm
    @Override
    public EditCategoryDto createCategory(EditCategoryDto request) {
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new ResourceConflictException("Slug '" + request.getSlug() + "' đã tồn tại cần tạo lại.");
        }

        ProductCategory category = new ProductCategory();
        category.setCategoryName(request.getCategoryName());
        category.setSlug(request.getSlug());

        ProductCategory saved = categoryRepository.save(category);

        return productCategoryMapper.toEditCategoryDto(saved);
    }

    // Cập nhật danh mục
    @Override
    public EditCategoryDto updateCategory(Long id, EditCategoryDto dto) {
        ProductCategory existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Danh mục không tồn tại với ID: " + id));

        boolean nameChanged = !existing.getCategoryName().equalsIgnoreCase(dto.getCategoryName());
        boolean slugChanged = !existing.getSlug().equalsIgnoreCase(dto.getSlug());

        if (nameChanged && !slugChanged) {
            throw new BadRequestException("Khi thay đổi tên danh mục, bạn phải thay đổi cả slug.");
        }

        if (slugChanged && categoryRepository.existsBySlug(dto.getSlug())) {
            throw new BadRequestException("Slug đã tồn tại.");
        }

        if (nameChanged && categoryRepository.existsByCategoryNameIgnoreCase(dto.getCategoryName())) {
            throw new BadRequestException("Tên danh mục đã tồn tại.");
        }

        productCategoryMapper.toEntity(existing, dto);
        ProductCategory updated = categoryRepository.save(existing);

        return productCategoryMapper.toEditCategoryDto(updated);
    }

    // Lấy chi tiết danh mục theo ID
    @Override
    public EditCategoryDto getById(Long id) {
        ProductCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục với ID: " + id));

        return productCategoryMapper.toEditCategoryDto(category);
    }

    // Xoá danh mục
    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tồn tại danh mục với ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    // Lấy tất cả danh mục có kèm sản phẩm và xử lý voucher hết hạn
    @Override
    @Transactional
    public List<CategorySummaryDto> getAllCategoriesWithProducts() {
        List<ProductCategory> categories = categoryRepository.getAllCategoriesWithProducts();
        LocalDateTime now = LocalDateTime.now();

        categories.forEach(cat ->
                cat.getProducts().forEach(prod -> {
                    Voucher voucher = prod.getVoucher();
                    boolean invalid = voucher == null ||
                            voucher.getDiscountPercent() == null ||
                            voucher.getDateEnd() == null ||
                            voucher.getDateEnd().isBefore(now);

                    if (invalid && voucher != null) {
                        prod.setVoucher(null);
                        voucher.setStatus(false);
                    }
                })
        );

        categoryRepository.saveAll(categories);
        return categories.stream()
                .map(categorySummaryMapper::toDto)
                .toList();
    }

    // Lấy danh mục + sản phẩm theo slug
    @Override
    public Optional<CategorySummaryDto> getCateWithProductsBySlug(String slug) {
        return categoryRepository.getCateWithProductsBySlug(slug)
                .map(categorySummaryMapper::toDto);
    }

    // Lấy tất cả danh mục dạng danh sách (ListItemDto)
    @Override
    public List<CategoryListItemDto> getAllCategoryListItems() {
        return categoryRepository.findAll()
                .stream()
                .map(productCategoryMapper::toCategoryListItemDto)
                .toList();
    }

    // Lấy danh mục có phân trang
    @Override
    public PaginatedResponseDto<CategoryListItemDto> getPaginatedProductsCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductCategory> categoryPage = categoryRepository.findAllProductCategory(pageable);

        List<CategoryListItemDto> dtos = categoryPage.getContent()
                .stream()
                .map(productCategoryMapper::toCategoryListItemDto)
                .toList();

        return new PaginatedResponseDto<>(
                dtos,
                categoryPage.getNumber(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements()
        );
    }

    // Lấy 5 danh mục đầu tiên (ListItemDto)
    @Override
    public List<CategoryListItemDto> getCategoryListItemDtos() {
        Pageable pageable = PageRequest.of(0, 5);
        return categoryRepository.findAllProductCategory(pageable)
                .stream()
                .map(productCategoryMapper::toCategoryListItemDto)
                .toList();
    }

    // Không sử dụng các hàm này trong BaseService → để rõ ràng
    @Override
    public ProductCategory create(ProductCategory dto) {
        throw new UnsupportedOperationException("Không hỗ trợ tạo ProductCategory trực tiếp.");
    }

    @Override
    public ProductCategory update(Long aLong, ProductCategory dto) {
        throw new UnsupportedOperationException("Không hỗ trợ cập nhật ProductCategory trực tiếp.");
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
    public Boolean existsById(Long aLong) {
        return false;
    }
}
