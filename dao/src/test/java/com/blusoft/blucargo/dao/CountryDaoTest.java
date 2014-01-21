package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.Country;

public class CountryDaoTest extends AbstractDAOTest {

	@Autowired
	private CountryDao countryDao;

	@Test
	public void testGetCountries() {
		Country country = new Country();
		country.setIso_3166_1_alfa_2("aaa");

		countryDao.saveOrUpdate(country);
		assertTrue(countryDao.findAll().size() > 0);

	}
}
