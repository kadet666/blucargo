package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.Country;

public interface CountryService {

	public void saveCountries(List<Country> countries);

	public void saveCountry(Country c);

	public List<Country> findAll();

	public List<Country> findAllCountry();

	public Country findById(long id);

}
