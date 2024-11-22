package com.ubo.weather.repository;

import com.ubo.weather.repository.client.OpenWeatherClient;
import dto.openmeteoapi.Model200;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class WeatherRepository {

  @Inject
  private OpenWeatherClient openWeatherClient;

  public Model200 getWeatherCityByName(String cityName, String token) {
    return openWeatherClient.getWeather(cityName, token);
  }
}
