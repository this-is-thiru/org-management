package com.application.ene.orgmanagement.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {

        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches = List.of(
                new CaffeineCache("users",
                        Caffeine.newBuilder()
                                .maximumSize(1000)
                                .expireAfterWrite(Duration.ofMinutes(5))
                                .build())

//                new CaffeineCache("products",
//                        Caffeine.newBuilder()
//                                .maximumSize(500)
//                                .expireAfterWrite(Duration.ofHours(1))
//                                .build()),
//
//                new CaffeineCache("permissions",
//                        Caffeine.newBuilder()
//                                .maximumSize(2000)
//                                .expireAfterWrite(Duration.ofMinutes(30))
//                                .build())
        );

        cacheManager.setCaches(caches);
        return cacheManager;
    }
}