package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.City;

public class CityDaoTest extends AbstractDAOTest {

	@Autowired
	private CityDao cityDao;

	@Test
	public void testGetCitiesByCountry() {
		City c1 = new City();
		c1.setCity("aaaa");
		c1.setCountry("pl");

		City c2 = new City();
		c2.setCity("bbbb");
		c2.setCountry("pl");

		City c3 = new City();
		c3.setCity("cccc");
		c3.setCountry("be");

		cityDao.saveOrUpdate(c1);
		cityDao.saveOrUpdate(c2);
		cityDao.saveOrUpdate(c3);

		List<City> cities = cityDao.findAll();

		assertEquals(cityDao.findCitiesByCountry("pl").size(), 2);

	}

}
