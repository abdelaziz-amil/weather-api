package com.ubo.weather.configuration;

import com.ubo.weather.repository.client.OpenWeatherClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("TEST")
public class FeignConfigTest {

  @Bean
  public OpenWeatherClient openWeatherClient() {
    return mock(OpenWeatherClient.class);
  }
}
