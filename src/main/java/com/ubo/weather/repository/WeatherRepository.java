package com.ubo.weather.repository;

import com.ubo.weather.repository.client.OpenWeatherClient;
import dto.openmeteoapi.Model200;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@Named
@Slf4j
public class WeatherRepository {

  @Inject
  private OpenWeatherClient openWeatherClient;

  @Cacheable("weather")
  public Model200 getWeatherCityByName(String cityName) {
    log.error("----------- Going to rest call for city {}", cityName);
    return openWeatherClient.getWeather(cityName);
  }
}
