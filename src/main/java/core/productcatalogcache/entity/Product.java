package core.productcatalogcache.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    private String category;
    private Integer stock;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;

    @PrePersist
    public void setCreatedDate() {
        createdDate = LocalDateTime.now();
        lastUpdatedDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(name, product.name) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    //- ID (Long)
//- Name (String, not null)
//- Description (String)
//- Price (BigDecimal, not null)
//- Category (String)
//- Stock (Integer)
//- Created Date (LocalDateTime)
//- Last Updated Date (LocalDateTime)
}
