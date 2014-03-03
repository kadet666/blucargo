package com.blusoft.blucargo.gwt.server;

import java.util.List;

import com.blusoft.blucargo.gwt.client.CityGWTService;
import com.blusoft.blucargo.model.City;
import com.blusoft.blucargo.service.CityService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CityGWTServiceImpl extends RemoteServiceServlet implements CityGWTService {

	private CityService CityService;

	public CityService getCityService() {
		if (CityService == null) {
			CityService = (CityService) SpringHelper.getBean("CityService", getServletContext());
		}

		return CityService;
	}

	public void saveCities(List<City> cities) {
		getCityService().saveCities(cities);

	}

	public void saveCity(City c) {
		getCityService().saveCity(c);
	}

	public List<City> findAll() {
		return getCityService().findAll();
	}

	public City findById(long id) {
		return getCityService().findById(id);
	}

	public List<City> findCitiesByCountry(String country) {
		return getCityService().findCitiesByCountry(country);
	}

	public List<String> findCityNamesByCountry(String country) {
		return getCityService().findCityNamesByCountry(country);
	}

}
