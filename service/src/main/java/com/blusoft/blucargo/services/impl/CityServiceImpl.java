package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.CityDao;
import com.blusoft.blucargo.model.City;
import com.blusoft.blucargo.services.CityService;

@Transactional
@Service("CityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	public synchronized void saveCities(List<City> cities) {
		for (City c : cities) {
			saveCity(c);
		}
	}

	public synchronized void saveCity(City c) {
		cityDao.saveOrUpdate(c);
	}

	public synchronized List<City> findAll() {
		return cityDao.findAll();
	}

	public synchronized City findById(long id) {
		return cityDao.findById(id);
	}

	public List<City> findCitiesByCountry(String country) {
		return cityDao.findCitiesByCountry(country);
	}

	public List<String> findCityNamesByCountry(String country) {
		return cityDao.findCityNamesByCountry(country);
	}

}
