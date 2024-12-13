package com.ubo.weather.business;

import com.ubo.weather.entity.CityEntity;
import com.ubo.weather.exception.CityAlreadyExistException;
import com.ubo.weather.repository.CityRepository;
import dto.weatherapi.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CityBusinessTest {

  @Mock
  private CityRepository cityRepository;

  @InjectMocks
  private CityBusiness cityBusiness;

  @Test
  void testCreatCityOk() {
    var cities = new ArrayList<City>();
    var city1 = new City();
    city1.setId("1");
    city1.setName("Paris");
    city1.setCountry("France");
    city1.setZipCode(75000);
    cities.add(city1);
    Mockito.when(cityRepository.getCitiesByName(any())).thenReturn(new ArrayList<>());
    Mockito.doNothing().when(cityRepository).createCity(any());

    var cityEntity = new CityEntity();
    cityEntity.setId("1");
    cityEntity.setName("Paris");
    cityEntity.setCountry("France");
    cityEntity.setZipcode(75000);
    cityEntity.setLat(48);
    cityEntity.setLon(2);
    try {
      cityBusiness.createCity(cityEntity);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testCreatCityAlreadyExist() {
    var cities = new ArrayList<City>();
    var city1 = new City();
    city1.setId("1");
    city1.setName("Paris");
    city1.setCountry("France");
    city1.setZipCode(75000);
    cities.add(city1);
    Mockito.when(cityRepository.getCitiesByName(any())).thenReturn(cities);

    var cityEntity = new CityEntity();
    cityEntity.setId("1");
    cityEntity.setName("Paris");
    cityEntity.setCountry("France");
    cityEntity.setZipcode(75000);
    cityEntity.setLat(48);
    cityEntity.setLon(2);
    try {
      cityBusiness.createCity(cityEntity);
      Assertions.assertThrows(CityAlreadyExistException.class, () -> cityBusiness.createCity(cityEntity));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
