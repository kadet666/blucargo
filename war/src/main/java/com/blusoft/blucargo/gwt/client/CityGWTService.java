package com.blusoft.blucargo.gwt.client;

import java.util.List;

import com.blusoft.blucargo.model.City;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CityGWTService")
public interface CityGWTService extends RemoteService {

	public void saveCities(List<City> cities);

	public void saveCity(City c);

	public List<City> findAll();

	public City findById(long id);

	public List<City> findCitiesByCountry(String country);

	public List<String> findCityNamesByCountry(String country);

}
