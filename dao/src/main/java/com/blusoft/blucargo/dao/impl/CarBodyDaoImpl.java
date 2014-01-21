package com.blusoft.blucargo.dao.impl;

import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.CarBodyDao;
import com.blusoft.blucargo.model.CarBody;

@Repository("carBody")
public class CarBodyDaoImpl extends BaseDaoImpl<CarBody> implements CarBodyDao {

	protected CarBodyDaoImpl() {
		super(CarBody.class);
	}

}
