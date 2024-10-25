package com.ubo.weather.business;

import com.ubo.weather.entity.CityEntity;
import com.ubo.weather.exception.CityAlreadyExistException;
import com.ubo.weather.exception.CityDoesntExistException;
import com.ubo.weather.exception.WeatherException;
import com.ubo.weather.mapper.CityMapper;
import com.ubo.weather.repository.CityRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.stream.Collectors;

@Named
public class CityBusiness {

  @Inject
  CityRepository cityRepository;

  public void createCity(CityEntity city) throws CityAlreadyExistException {
    String cityName = city.getName();
    if (doesCityAlreadyExist(cityName))
      throw new CityAlreadyExistException("a city with this id already exists");

    cityRepository.createCity(CityMapper.toDto(city));
  }

  public List<CityEntity> getAllCities() {
    return CityMapper.toListEntity(cityRepository.getAllCities());
  }

  public CityEntity getCityByListName(List<String> name) {
    return CityMapper.toEntity(cityRepository.getCityByListName(name));
  }


  public void updateCity(CityEntity entity) throws WeatherException {
    if (!doesCityAlreadyExist(entity.getName()))
      throw new WeatherException("CITY_ALREADY_EXIST", entity.getName() + " already exist");
    cityRepository.updateCity(CityMapper.toDto(entity));
  }

  private boolean doesCityAlreadyExist(String cityName) {
    List<CityEntity> cityEntityList = CityMapper.toListEntity(cityRepository.getCitiesByName(List.of(cityName)));
    if (cityEntityList.isEmpty()) return false;
    return true;
  }

  public CityEntity deleteCityById(Long id) throws CityDoesntExistException {
    CityEntity cityRemoved = CityMapper.toEntity(cityRepository.getCityById(id));
    cityRepository.deleteCityById(id);
    return cityRemoved;
  }

  public List<CityEntity> getListCityById(List<String> listId) {
    return cityRepository.getCitiesById(listId)
            .stream()
            .map(CityMapper::toEntity)
            .collect(Collectors.toList());
  }

  public List<CityEntity> getListCityByName(List<String> listNames) {
    return cityRepository.getCitiesByName(listNames)
            .stream()
            .map(CityMapper::toEntity)
            .collect(Collectors.toList());
  }
}
