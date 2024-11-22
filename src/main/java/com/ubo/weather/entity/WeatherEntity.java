package com.ubo.weather.entity;

import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class WeatherEntity {
  private Integer temperature;
  private Date date;
}
