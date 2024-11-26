package core.productcatalogcache.service.impl;

import core.productcatalogcache.dto.ProductRequestDto;
import core.productcatalogcache.dto.ProductResponseDto;
import core.productcatalogcache.entity.Product;
import core.productcatalogcache.mapper.ProductMapper;
import core.productcatalogcache.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateNewProduct() {
        ProductRequestDto requestDto = new ProductRequestDto("Test Product", "Description", new BigDecimal("10.00"), "Category", 10);
        Product productEntity = new Product(1L, "Test Product", "Description", new BigDecimal("10.00"), "Category", 10, LocalDateTime.now(), LocalDateTime.now());
        ProductResponseDto responseDto = new ProductResponseDto(1L, "Test Product", "Description", new BigDecimal("10.00"), "Category", 10, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductResponseDto result = productService.createNewProduct(requestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);
        Mockito.verify(productMapper).requestToEntity(requestDto);
        Mockito.verify(productRepository).save(productEntity);
        Mockito.verify(productMapper).entityToResponseDto(productEntity);
    }

    @Test
    void testGetProduct() {
        Long productId = 1L;
        Product productEntity = new Product(productId, "Test Product", "Description", new BigDecimal("10.00"), "Category", 10, LocalDateTime.now(), LocalDateTime.now());
        ProductResponseDto responseDto = new ProductResponseDto(productId, "Test Product", "Description", new BigDecimal("10.00"), "Category", 10, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        ProductResponseDto result = productService.getProduct(productId);

        assertNotNull(result);
        assertEquals(responseDto, result);

        Mockito.verify(productRepository).findById(productId);
        Mockito.verify(productMapper).entityToResponseDto(productEntity);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;

        ProductRequestDto requestDto = new ProductRequestDto("Updated Product", "Updated Description", new BigDecimal("20.00"), "Updated Category", 5);
        Product existingProduct = new Product(productId, "Test Product", "Description", new BigDecimal("10.00"), "Category", 10, LocalDateTime.now(), LocalDateTime.now());
        ProductResponseDto responseDto = new ProductResponseDto(productId, "Updated Product", "Updated Description", new BigDecimal("20.00"), "Updated Category", 5, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        ProductResponseDto result = productService.updateProduct(productId, requestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);

        assertEquals("Updated Product", existingProduct.getName());
        assertEquals("Updated Description", existingProduct.getDescription());
        assertEquals(new BigDecimal("20.00"), existingProduct.getPrice());
        assertEquals("Updated Category", existingProduct.getCategory());
        assertEquals(5, existingProduct.getStock());

        Mockito.verify(productRepository).findById(productId);
        Mockito.verify(productMapper).entityToResponseDto(existingProduct);
    }
}
