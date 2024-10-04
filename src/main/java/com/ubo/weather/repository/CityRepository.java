package com.ubo.weather.repository;

import dto.weatherapi.City;
import dto.weatherapi.CityCoordinate;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class CityRepository {
  private static final String SQL_INSERT_CITY = "INSERT INTO city (ID,NAME,ZIPCODE,REGION,COUNTRY,LONGITUDE,LATITUDE) VALUES " +
          "(:id,:name,:zipcode,:region,:country,:longitude,:latitude)";
  private static final String SQL_SELECT_CITIES = "SELECT * FROM city";
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

  public List<City> getAllCities() {
     List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_SELECT_CITIES, new HashMap<>());
    return list.stream().map(row -> {
      City city = new City();
      city.setCountry(row.get("country").toString());
      city.setId(row.get("id").toString());
      city.setName(row.get("name").toString());
      city.setRegion(row.get("region").toString());
      city.setZipCode(Integer.parseInt(row.get("zipCode").toString()));
      CityCoordinate coordinate = new CityCoordinate();
      coordinate.setLatitude(Integer.parseInt(row.get("latitude").toString()));
      coordinate.setLongitude(Integer.parseInt(row.get("longitude").toString()));
      city.setCoordinate(coordinate);
      return city;
    }).toList();
  }
}
