package com.ubo.weather.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubo.weather.repository.client.OpenWeatherClient;
import com.ubo.weather.repository.client.OpenWeatherInterceptor;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import jakarta.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@Profile("!TEST")
public class FeignConfig {

  @Inject
  private ObjectMapper objectMapper;

  @Inject
  OpenWeatherInterceptor openWeatherInterceptor;

  @Bean
  OpenWeatherClient getOpenWeatherClient() {
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
            .custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slidingWindowSize(20)
            .build();
    CircuitBreaker circuitBreaker = CircuitBreaker.of("OpenWeatherCircuitBreaker", circuitBreakerConfig);
    FeignDecorators decorators = FeignDecorators
            .builder()
            .withCircuitBreaker(circuitBreaker)
            .build();
    return Resilience4jFeign.builder(decorators)
            .encoder(new JacksonEncoder(objectMapper))
            .decoder(new JacksonDecoder(objectMapper))
            .requestInterceptor(openWeatherInterceptor)
            .logger(new Logger.JavaLogger(FeignConfig.class))
            .logLevel(Logger.Level.FULL)
            .client(new OkHttpClient(getOkHttpClient()))
            .target(OpenWeatherClient.class, "https://api.openweathermap.org/data/2.5");
  }

  okhttp3.OkHttpClient getOkHttpClient() {
    var okHttpClient = new okhttp3.OkHttpClient().newBuilder();
    okHttpClient.connectTimeout(1000, TimeUnit.MILLISECONDS);
    okHttpClient.readTimeout(1000, TimeUnit.MILLISECONDS);
    return okHttpClient.build();
  }
}
