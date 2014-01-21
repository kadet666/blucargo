package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.CountryDao;
import com.blusoft.blucargo.model.Country;

@Repository("country")
public class CountryDaoImpl extends BaseDaoImpl<Country> implements CountryDao {

	protected CountryDaoImpl() {
		super(Country.class);
	}

	@SuppressWarnings("unchecked")
	public List<Country> findAllCountry() {
		String queryString = "SELECT c from Country c";
		Query query = getSession().createQuery(queryString);
		return (List<Country>) query.list();
	}

}
