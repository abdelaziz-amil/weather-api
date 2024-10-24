package com.ubo.weather.business;

import com.ubo.weather.entity.CityEntity;
import com.ubo.weather.exception.CityAlreadyExistException;
import com.ubo.weather.exception.CityDoesntExistException;
import com.ubo.weather.mapper.CityMapper;
import com.ubo.weather.repository.CityRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
public class CityBusiness {

  @Inject
  CityRepository cityRepository;

  public void createCity(CityEntity city) throws CityAlreadyExistException {
    String cityId = city.getId();
    if (doesCityAlreadyExist(cityId))
      throw new CityAlreadyExistException("a city with this id already exists");

    cityRepository.createCity(CityMapper.toDto(city));
  }

  public List<CityEntity> getAllCities() {
    return CityMapper.toListEntity(cityRepository.getAllCities());
  }

  public CityEntity getCityByListName(List<String> name) {
    return CityMapper.toEntity(cityRepository.getCityByListName(name));
  }

  public void updateCity(CityEntity entity) throws CityDoesntExistException {
    if (!doesCityAlreadyExist(entity.getId()))
      throw new CityDoesntExistException("the city given doesn't exist");
    cityRepository.updateCity(CityMapper.toDto(entity));
  }

  private boolean doesCityAlreadyExist(String cityId) {
    boolean doesCityAlreadyExist =  false;
    List<CityEntity> cityEntityList = CityMapper.toListEntity(cityRepository.getAllCities());
    for (CityEntity cityEntity : cityEntityList) {
      if (cityId.equals(cityEntity.getId())) {
        doesCityAlreadyExist = true;
        break;
      }
    }
    return doesCityAlreadyExist;
  }

  public CityEntity deleteCityById(Long id) throws CityDoesntExistException {
    CityEntity cityRemoved = CityMapper.toEntity(cityRepository.getCityById(id));
    cityRepository.deleteCityById(id);
    return cityRemoved;
  }
}
