package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.CarBody;

public interface CarBodyService {

	public void saveBodies(List<CarBody> bodies);

	public void saveBody(CarBody b);

	public List<CarBody> findAll();

	public CarBody findById(long id);

}
