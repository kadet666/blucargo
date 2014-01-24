package com.blusoft.blucargo.service;

import java.util.List;

import com.blusoft.blucargo.model.City;

public interface CityService {

	public void saveCities(List<City> cities);

	public void saveCity(City c);

	public List<City> findAll();

	public City findById(long id);

	public List<City> findCitiesByCountry(String country);

	public List<String> findCityNamesByCountry(String country);

}
