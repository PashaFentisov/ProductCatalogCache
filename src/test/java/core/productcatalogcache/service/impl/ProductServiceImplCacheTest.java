package core.productcatalogcache.service.impl;

import core.productcatalogcache.dto.ProductResponseDto;
import core.productcatalogcache.entity.Product;
import core.productcatalogcache.mapper.ProductMapper;
import core.productcatalogcache.repository.ProductRepository;
import core.productcatalogcache.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceImplCacheTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    @Test
    void testCacheableGetProduct() {
        Long productId = 1L;
        Product product = new Product(productId, "Cached Product", "Description", new BigDecimal("15.00"), "Category", 5, LocalDateTime.now(), LocalDateTime.now());
        ProductResponseDto responseDto = new ProductResponseDto(productId, "Cached Product", "Description", new BigDecimal("15.00"), "Category", 5, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.entityToResponseDto(product)).thenReturn(responseDto);

        ProductResponseDto firstCall = productService.getProduct(productId);
        ProductResponseDto secondCall = productService.getProduct(productId);

        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertEquals(firstCall, secondCall);
        Mockito.verify(productRepository, Mockito.times(1)).findById(productId);

        Cache cache = cacheManager.getCache("products");
        assertNotNull(cache.get(productId));
    }
}
