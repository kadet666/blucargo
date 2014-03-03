package com.blusoft.blucargo.gwt.server;

import java.util.List;

import com.blusoft.blucargo.gwt.client.CountryGWTService;
import com.blusoft.blucargo.model.Country;
import com.blusoft.blucargo.service.CountryService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CountryGWTServiceImpl extends RemoteServiceServlet implements CountryGWTService {

	private CountryService countryService;

	public CountryService getCountryService() {
		if (countryService == null) {
			countryService = (CountryService) SpringHelper.getBean("CountryService", getServletContext());
		}

		return countryService;
	}

	public void saveCountries(List<Country> countries) {
		getCountryService().saveCountries(countries);
	}

	public void saveCountry(Country c) {
		getCountryService().saveCountry(c);
	}

	public List<Country> findAll() {
		return getCountryService().findAll();
	}

	public List<Country> findAllCountry() {
		return getCountryService().findAllCountry();
	}

	public Country findById(long id) {
		return getCountryService().findById(id);
	}

}
