package com.accompany.stickyrice.service.impl;

import com.accompany.stickyrice.dto.request.EditProductDto;
import com.accompany.stickyrice.dto.response.ProductItemDto;
import com.accompany.stickyrice.dto.response.ProductListItemDto;
import com.accompany.stickyrice.dto.response.PaginatedResponseDto;
import com.accompany.stickyrice.entity.Product;
import com.accompany.stickyrice.entity.ProductCategory;
import com.accompany.stickyrice.mapper.ProductMapper;
import com.accompany.stickyrice.repository.CategoryRepository;
import com.accompany.stickyrice.repository.ProductRepository;
import com.accompany.stickyrice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(Product dto) {
        return null;
    }

    @Override
    public Product update(Long aLong, Product dto) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long aLong) {
    }

    @Override
    public Boolean existsById(Long aLong) {
        return null;
    }

    // ✅ Lấy top 5 sản phẩm đầu tiên
    @Override
    public List<ProductListItemDto> getProductListItem() {
        Pageable pageable = PageRequest.of(0, 5);
        return productRepository.findAllProducts(pageable)
                .stream()
                .map(productMapper::toProductListItemDto)
                .toList();
    }

    // ✅ Sử dụng PaginatedResponseDto thay vì Map
    @Override
    public PaginatedResponseDto<ProductListItemDto> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAllProducts(pageable);

        List<ProductListItemDto> dtos = productPage.getContent()
                .stream()
                .map(productMapper::toProductListItemDto)
                .toList();

        return new PaginatedResponseDto<>(
                dtos,
                productPage.getNumber(),
                productPage.getTotalPages(),
                productPage.getTotalElements()
        );
    }


    public Optional<EditProductDto> getProductItem(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toEditProductDto);
    }


    // ✅ Tạo sản phẩm mới — đã loại bỏ voucher
    @Override
    public ProductItemDto createProduct(com.accompany.stickyrice.dto.request.CreateProductDto dto) {
        ProductCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục sản phẩm"));

        Product product = productMapper.toEntity(dto, category);
        productRepository.save(product);

        return productMapper.toProductItemDto(product);
    }

    @Override
    public Product editProductByDto(Long id, EditProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại"));

        // Lấy category để set lại
        ProductCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại"));

        // Gán dữ liệu từ DTO vào Entity
        existing.setProductName(dto.getProductName());
        existing.setProductImage(dto.getProductImage());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setIsActive(dto.getActive());
        existing.setSlug(dto.getSlug());
        existing.setProductCategory(category);

        return productRepository.save(existing);
    }

    @Override
    public void updateProduct(Long id, String productName, String slug, Double price, Long categoryId,
                              String description, Boolean isActive, MultipartFile imageFile) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        product.setProductName(productName);
        product.setSlug(slug);
        product.setPrice(price);

        // ✅ sửa ở đây
        ProductCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Danh mục không tồn tại"));
        product.setProductCategory(category);

        product.setDescription(description);
        product.setIsActive(isActive);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = saveFile(imageFile);
                product.setProductImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Không thể lưu ảnh: " + e.getMessage());
            }
        }

        productRepository.save(product);
    }


    @Override
    public String uploadImage(MultipartFile file) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/images/";
            Path uploadPath = Paths.get(uploadDir);

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

            return newFileName;

        } catch (IOException e) {
            throw new RuntimeException("Upload image failed: " + e.getMessage());
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(System.getProperty("user.dir") + "/uploads/images/");

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

        return newFileName;
    }


}
