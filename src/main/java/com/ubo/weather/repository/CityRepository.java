package com.ubo.weather.repository;

import dto.weatherapi.City;
import dto.weatherapi.CityCoordinate;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class CityRepository {
  private static final String SQL_INSERT_CITY = "INSERT INTO city (ID,NAME,ZIPCODE,REGION,COUNTRY,LONGITUDE,LATITUDE) VALUES " +
          "(:id,:name,:zipcode,:region,:country,:longitude,:latitude)";
  private static final String SQL_SELECT_CITIES = "SELECT * FROM city";
  private static final String SQL_SELECT_CITY_BY_NAME = "SELECT " +
          "ID,NAME,ZIPCODE,REGION,COUNTRY,LONGITUDE,LATITUDE" +
          " FROM city WHERE NAME in (:names)";
  private static final String SQL_SELECT_CITY_BY_ID = "SELECT * FROM city WHERE ID=:id";
  private static final String SQL_UPDATE_CITY = "UPDATE city SET NAME=:name, ZIPCODE=:zipcode, REGION=:region, COUNTRY=:country, LONGITUDE=:longitude, LATITUDE=:latitude WHERE ID=:id";
  private static final String SQL_DELETE_CITY = "DELETE FROM city WHERE ID=:id";
  private static final String SQL_GET_CITIES_BY_ID = "SELECT " +
          "ID,NAME,ZIPCODE,REGION,COUNTRY,LONGITUDE,LATITUDE" +
          " FROM city WHERE ID in (:ids)";

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

  public City getCityByListName(List<String> name) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("name",name);

    return jdbcTemplate.queryForObject(
            SQL_SELECT_CITY_BY_NAME,
            map,
            BeanPropertyRowMapper.newInstance(City.class));
  }

  public void updateCity(City dto) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("id",dto.getId());
    map.put("name",dto.getName());
    map.put("zipCode",dto.getZipCode());
    map.put("region",dto.getRegion());
    map.put("country",dto.getCountry());
    map.put("longitude",dto.getCoordinate().getLongitude());
    map.put("latitude",dto.getCoordinate().getLatitude());

    jdbcTemplate.update(SQL_UPDATE_CITY,map);
  }

  public void deleteCityById(Long id) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("id",id);

    jdbcTemplate.update(SQL_DELETE_CITY, map);
  }

  public City getCityById(Long id) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("id",id);

    return jdbcTemplate.queryForObject(
            SQL_SELECT_CITY_BY_ID,
            map,
            BeanPropertyRowMapper.newInstance(City.class));
  }

  public List<City> getCitiesByName(List<String> names) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("names",names);

    List<City> cities = jdbcTemplate.query(SQL_SELECT_CITY_BY_NAME, map, (r, s) ->{
      City city = new City();
      city.setName(r.getString("NAME"));
      city.setName(r.getString("COUNTRY"));
      city.setRegion(r.getString("REGION"));
      city.setId(r.getString("ID"));
      CityCoordinate coordinate = new CityCoordinate();
      coordinate.setLatitude(r.getInt("LATITUDE"));
      coordinate.setLongitude(r.getInt("LONGITUDE"));
      city.setCoordinate(coordinate);
      return city;
    });

    return cities;
  }

  public List<City> getCitiesById(List<String> ids) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("names",ids);

    List<City> cities = jdbcTemplate.query(SQL_SELECT_CITY_BY_ID, map, (r, s) ->{
      City city = new City();
      city.setName(r.getString("NAME"));
      city.setName(r.getString("COUNTRY"));
      city.setRegion(r.getString("REGION"));
      city.setId(r.getString("ID"));
      CityCoordinate coordinate = new CityCoordinate();
      coordinate.setLatitude(r.getInt("LATITUDE"));
      coordinate.setLongitude(r.getInt("LONGITUDE"));
      city.setCoordinate(coordinate);
      return city;
    });

    return cities;
  }
}
