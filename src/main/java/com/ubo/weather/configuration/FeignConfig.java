package com.ubo.weather.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubo.weather.repository.client.OpenWeatherClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import jakarta.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.TimeUnit;

@Configuration
@Profile("!TEST")
public class FeignConfig {

  @Inject
  private ObjectMapper objectMapper;

  @Bean
  OpenWeatherClient getOpenWeatherClient() {
    return Feign.builder()
            .encoder(new JacksonEncoder(objectMapper))
            .decoder(new JacksonDecoder(objectMapper))
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
