package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.CountryDao;
import com.blusoft.blucargo.model.Country;
import com.blusoft.blucargo.service.CountryService;

@Transactional
@Service("CountryService")
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	public synchronized void saveCountries(List<Country> countries) {
		for (Country c : countries) {
			saveCountry(c);
		}
	}

	public synchronized void saveCountry(Country c) {
		countryDao.saveOrUpdate(c);
	}

	public synchronized List<Country> findAll() {
		return countryDao.findAll();
	}

	public synchronized List<Country> findAllCountry() {
		return countryDao.findAllCountry();
	}

	public synchronized Country findById(long id) {
		return countryDao.findById(id);
	}

}
