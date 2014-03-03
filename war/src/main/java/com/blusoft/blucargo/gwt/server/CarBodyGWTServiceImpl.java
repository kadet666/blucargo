package com.blusoft.blucargo.gwt.server;

import java.util.List;

import com.blusoft.blucargo.gwt.client.CarBodyGWTService;
import com.blusoft.blucargo.model.CarBody;
import com.blusoft.blucargo.service.CarBodyService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CarBodyGWTServiceImpl extends RemoteServiceServlet implements CarBodyGWTService {

	private CarBodyService carBodyService;

	public CarBodyService getCarBodyService() {
		if (carBodyService == null) {
			carBodyService = (CarBodyService) SpringHelper.getBean("CarBodyService", getServletContext());
		}

		return carBodyService;
	}

	public void saveBodies(List<CarBody> bodies) {
		getCarBodyService().saveBodies(bodies);
	}

	public void saveBody(CarBody b) {
		getCarBodyService().saveBody(b);
	}

	public List<CarBody> findAll() {
		return getCarBodyService().findAll();
	}

	public CarBody findById(long id) {
		return getCarBodyService().findById(id);
	}

}
