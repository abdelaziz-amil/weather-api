package com.ubo.weather.mapper;

import com.ubo.weather.entity.CityEntity;
import dto.weatherapi.City;
import dto.weatherapi.CityCoordinate;

public class CityMapper {

  public static City toDto(CityEntity cityEntity) {
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
}
