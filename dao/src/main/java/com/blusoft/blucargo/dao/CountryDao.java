package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.Country;

public interface CountryDao extends BaseDao<Country> {

	public List<Country> findAllCountry();

}
