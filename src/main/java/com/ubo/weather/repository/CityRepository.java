package com.ubo.weather.repository;

import dto.weatherapi.City;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

@Named
public class CityRepository {
  private static final String SQL_INSERT_CITY = "INSERT INTO city (ID,NAME,ZIPCODE,REGION,COUNTRY,LONGITUDE,LATITUDE) VALUES " +
          "(:id,:name,:zipcode,:region,:country,:longitude,:latitude)";
  @Inject
  private NamedParameterJdbcTemplate jdbcTemplate;

  public void createCity(City city) {
    Map<String,Object> map = new HashMap<>();
    map.put("id",city.getId());
    map.put("name",city.getName());
    map.put("zipcode",city.getZipCode());
    map.put("region",city.getRegion());
    map.put("country",city.getCountry());
    map.put("longitude",city.getCoordinate().getLongitude());
    map.put("latitude",city.getCoordinate().getLatitude());

    jdbcTemplate.update(SQL_INSERT_CITY,map);
  }
}
