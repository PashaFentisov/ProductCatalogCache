package core.productcatalogcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductCatalogCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogCacheApplication.class, args);
    }
}

