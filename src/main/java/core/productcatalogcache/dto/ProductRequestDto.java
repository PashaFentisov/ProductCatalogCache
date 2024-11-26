package core.productcatalogcache.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotBlank(message = "Category is required")
    private String category;
    private Integer stock;
}
