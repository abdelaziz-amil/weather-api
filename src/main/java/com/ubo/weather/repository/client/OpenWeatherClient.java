package com.ubo.weather.repository.client;

import dto.openmeteoapi.Model200;
import feign.Param;
import feign.RequestLine;

public interface OpenWeatherClient {

  @RequestLine("GET /weather?q={cityName}&appid={token}")
  Model200 getWeather(@Param("cityName") String cityName, @Param("token") String token);
}
