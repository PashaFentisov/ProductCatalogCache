package core.productcatalogcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@Slf4j
public class ProductCatalogCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogCacheApplication.class, args);
    }

    @CacheEvict(allEntries = true, value = {"products", "productsByCategory"})
    @Scheduled(fixedDelay = 600000)
    public void productsCacheEvict() {
        log.info("Flush Cache " + LocalDateTime.now());
    }
}

