package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.CityDao;
import com.blusoft.blucargo.model.City;

@Repository("city")
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao {

	protected CityDaoImpl() {
		super(City.class);
	}

	public List<City> findCitiesByCountry(String country) {
		String queryString = "SELECT c from City c where c.country=:country";
		Query query = getSession().createQuery(queryString);
		query.setParameter("country", country);
		List<City> resultList = (List<City>) query.list();
		return resultList;
	}

	public List<String> findCityNamesByCountry(String country) {
		String queryString = "SELECT c.city from City c where c.country=:country";
		Query query = getSession().createQuery(queryString);
		query.setParameter("country", country);
		List<String> resultList = (List<String>) query.list();
		return resultList;
	}

}
