package com.ubo.weather.business;

import com.ubo.weather.entity.WeatherEntity;
import com.ubo.weather.mapper.WeatherMapper;
import com.ubo.weather.repository.WeatherRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class WeatherBusiness {

  @Inject
  WeatherRepository weatherRepository;

  public WeatherEntity getWeatherByCityName(String cityName) {
    return WeatherMapper.toEntity(weatherRepository.getWeatherCityByName(cityName));
  }

}
