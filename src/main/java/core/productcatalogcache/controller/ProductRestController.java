package core.productcatalogcache.controller;

import core.productcatalogcache.dto.ProductRequestDto;
import core.productcatalogcache.dto.ProductResponseDto;
import core.productcatalogcache.exception.BigSizeException;
import core.productcatalogcache.exception.EntityValidationException;
import core.productcatalogcache.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(@RequestParam(required = false, defaultValue = "0") int page,
                                                                @RequestParam(required = false, defaultValue = "10") int size,
                                                                @RequestParam(required = false, defaultValue = "id") String sort) {
        if (size > 100) {
            throw new BigSizeException("You can get maximum 100 products at one time");
        }
        Page<ProductResponseDto> products = productService.getProducts(PageRequest.of(page, size, Sort.by(sort)));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getCategory(id));
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByCategory(@RequestParam(required = false, defaultValue = "0") int page,
                                                                          @RequestParam(required = false, defaultValue = "10") int size,
                                                                          @RequestParam(required = false, defaultValue = "id") String sort,
                                                                          @PathVariable String category) {
        if (size > 100) {
            throw new BigSizeException("You can get maximum 100 products at one time");
        }
        Page<ProductResponseDto> products = productService.getProductsByCategory(
                PageRequest.of(page, size, Sort.by(sort)), category);

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createNewProduct(@RequestBody @Valid ProductRequestDto productRequestDto,
                                                               Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> log.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        ProductResponseDto savedProduct = productService.createNewProduct(productRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
                                                            @RequestBody @Valid ProductRequestDto productRequestDto,
                                                            Errors errors) {
        if (errors.hasErrors()) {
            errors.getFieldErrors().forEach(er -> log.error(er.getDefaultMessage()));
            throw new EntityValidationException("Validation failed", errors);
        }
        ProductResponseDto updatedProduct = productService.updateProduct(id, productRequestDto);
        return ResponseEntity.ok()
                .body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
