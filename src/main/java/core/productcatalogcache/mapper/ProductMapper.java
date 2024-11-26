package core.productcatalogcache.mapper;

import core.productcatalogcache.dto.ProductRequestDto;
import core.productcatalogcache.dto.ProductResponseDto;
import core.productcatalogcache.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto entityToResponseDto(Product product);

    Product requestToEntity(ProductRequestDto productRequestDto);
}
