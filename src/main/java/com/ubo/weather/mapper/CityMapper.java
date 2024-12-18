package com.ubo.weather.mapper;

import com.ubo.weather.entity.CityEntity;
import dto.weatherapi.City;
import dto.weatherapi.CityCoordinate;

import java.util.ArrayList;
import java.util.List;

public class CityMapper {

  public static City toDto(CityEntity cityEntity) {
    if (cityEntity == null) return null;
    City cityDto = new City();
    CityCoordinate cityCoordinate = new CityCoordinate();
    cityCoordinate.setLatitude(cityEntity.getLat());
    cityCoordinate.setLongitude(cityEntity.getLon());
    cityDto.setId(cityEntity.getId());
    cityDto.setName(cityEntity.getName());
    cityDto.setCoordinate(cityCoordinate);
    cityDto.setCountry(cityEntity.getCountry());
    cityDto.setZipCode(cityEntity.getZipcode());
    return cityDto;
  }

  public static CityEntity toEntity(City city) {
    if (city == null) return null;
    CityEntity cityEntity = new CityEntity();
    cityEntity.setCountry(city.getCountry());
    cityEntity.setId(city.getId());
    cityEntity.setName(city.getName());
    cityEntity.setRegion(city.getRegion());
    cityEntity.setZipcode(city.getZipCode());
    cityEntity.setLon(city.getCoordinate().getLongitude());
    cityEntity.setLat(city.getCoordinate().getLatitude());
    return cityEntity;
  }

  public static List<City> toListDto(List<CityEntity> cities) {
    List<City> citiesDto = new ArrayList<>();
    for (CityEntity cityEntity : cities) {
      citiesDto.add(toDto(cityEntity));
    }
    return citiesDto;
  }

  public static List<CityEntity> toListEntity(List<City> cities) {
    if (cities == null || cities.isEmpty()) return null;
    List<CityEntity> citiesEntity = new ArrayList<>();
    for (City city : cities) {
      citiesEntity.add(toEntity(city));
    }
    return citiesEntity;
  }
}
