package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.CarBodyDao;
import com.blusoft.blucargo.model.CarBody;
import com.blusoft.blucargo.service.CarBodyService;

@Transactional
@Service("CarBodyService")
public class CarBodyServiceImpl implements CarBodyService {

	@Autowired
	private CarBodyDao bodyDao;

	public synchronized void saveBodies(List<CarBody> bodies) {

		for (CarBody b : bodies) {
			saveBody(b);
		}

	}

	public synchronized void saveBody(CarBody b) {
		bodyDao.saveOrUpdate(b);
	}

	public synchronized List<CarBody> findAll() {
		return bodyDao.findAll();
	}

	public synchronized CarBody findById(long id) {
		return bodyDao.findById(id);
	}
}
