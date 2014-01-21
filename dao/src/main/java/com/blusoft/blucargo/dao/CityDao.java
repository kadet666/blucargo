package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.City;

public interface CityDao extends BaseDao<City> {

	public List<City> findCitiesByCountry(String country);

	public List<String> findCityNamesByCountry(String country);

}
