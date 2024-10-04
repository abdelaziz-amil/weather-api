package com.ubo.weather.business;

import com.ubo.weather.entity.CityEntity;
import com.ubo.weather.mapper.CityMapper;
import com.ubo.weather.repository.CityRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class CityBusiness {

  @Inject
  CityRepository cityRepository;

  public void createCity(CityEntity city) {
    cityRepository.createCity(CityMapper.toDto(city));
  }
}
