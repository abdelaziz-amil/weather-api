package com.ubo.weather.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CityEntity {
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }

  public int getLat() {
    return lat;
  }

  public void setLat(int lat) {
    this.lat = lat;
  }

  public int getLon() {
    return lon;
  }

  public void setLon(int lon) {
    this.lon = lon;
  }

  private String id;
  private String name;
  private String country;
  private String region;
  private int zipcode;
  private int lat;
  private int lon;
}
