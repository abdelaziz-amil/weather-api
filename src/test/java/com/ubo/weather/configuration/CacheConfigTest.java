package com.ubo.weather.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("TEST")
public class CacheConfigTest {

  @Bean
  public CacheManager cacheManager() {
    return mock(ConcurrentMapCacheManager.class);
  }
}
