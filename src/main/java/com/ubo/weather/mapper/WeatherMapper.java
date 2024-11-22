package com.ubo.weather.mapper;

import com.ubo.weather.entity.WeatherEntity;
import dto.openmeteoapi.Model200;
import dto.weatherapi.Weather;

import java.util.Date;

public class WeatherMapper {
  public static WeatherEntity toEntity(Model200 model200) {
    WeatherEntity weatherEntity = new WeatherEntity();
    weatherEntity.setDate(new Date());
    weatherEntity.setTemperature(model200.getMain().getTemp().intValue() - 273);
    return weatherEntity;
  }

  public static Weather toDto(WeatherEntity weatherEntity) {
    Weather dto = new Weather();
    dto.setTemperature(weatherEntity.getTemperature());
    return dto;
  }
}
