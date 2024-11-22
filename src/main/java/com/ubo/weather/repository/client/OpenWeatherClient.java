package com.ubo.weather.repository.client;

import dto.openmeteoapi.Model200;
import feign.Param;
import feign.RequestLine;

public interface OpenWeatherClient {

  @RequestLine("GET /weather?q={cityName}")
  Model200 getWeather(@Param("cityName") String cityName);
}
