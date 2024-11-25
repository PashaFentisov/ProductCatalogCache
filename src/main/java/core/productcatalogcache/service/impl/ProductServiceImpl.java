package core.productcatalogcache.service.impl;

import core.productcatalogcache.dto.ProductRequestDto;
import core.productcatalogcache.dto.ProductResponseDto;
import core.productcatalogcache.entity.Product;
import core.productcatalogcache.exception.EntityNotFoundException;
import core.productcatalogcache.mapper.ProductMapper;
import core.productcatalogcache.repository.ProductRepository;
import core.productcatalogcache.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final static String NOT_FOUND_EXCEPTION_MESSAGE = "Product with id %d not found";


    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest)
                .map(productMapper::entityToResponseDto);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long id) {
        return productRepository.findById(id)
                .map(productMapper::entityToResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    @Transactional
    public ProductResponseDto createNewProduct(ProductRequestDto productRequestDto) {
        Product savedEntity = productRepository.save(productMapper.requestToEntity(productRequestDto));
        return productMapper.entityToResponseDto(savedEntity);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, id)));
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(productRequestDto.getCategory());
        product.setStock(productRequestDto.getStock());
        product.setDescription(productRequestDto.getDescription());
        product.setLastUpdatedDate(LocalDateTime.now());
        return productMapper.entityToResponseDto(product);
    }

    @Override
    @Cacheable(value = "productsByCategory", key = "#category")
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProductsByCategory(PageRequest pageRequest, String category) {
        return productRepository.findByCategory(category, pageRequest)
                .map(productMapper::entityToResponseDto);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
