package com.blusoft.blucargo.gwt.client;

import java.util.List;

import com.blusoft.blucargo.model.Country;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CountryGWTService")
public interface CountryGWTService extends RemoteService {

	public void saveCountries(List<Country> countries);

	public void saveCountry(Country c);

	public List<Country> findAll();

	public List<Country> findAllCountry();

	public Country findById(long id);

}
