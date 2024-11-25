package core.productcatalogcache.service;

import core.productcatalogcache.dto.ProductRequestDto;
import core.productcatalogcache.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    Page<ProductResponseDto> getProducts(PageRequest pageRequest);

    ProductResponseDto getCategory(Long id);

    ProductResponseDto createNewProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    Page<ProductResponseDto> getProductsByCategory(PageRequest pageRequest, String category);

    void deleteProductById(Long id);
}
