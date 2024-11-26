package core.productcatalogcache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static core.productcatalogcache.util.CacheNamesUtil.PRODUCT_BY_CATEGORY_CACHE;
import static core.productcatalogcache.util.CacheNamesUtil.PRODUCT_BY_ID_CACHE;

@EnableCaching
@Configuration
@Slf4j
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                PRODUCT_BY_CATEGORY_CACHE,
                PRODUCT_BY_ID_CACHE);

        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .recordStats()
                        .maximumSize(1000)
                        .expireAfterWrite(10, java.util.concurrent.TimeUnit.MINUTES)
        );
        return cacheManager;
    }
}
