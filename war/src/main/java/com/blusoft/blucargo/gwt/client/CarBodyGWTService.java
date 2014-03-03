package com.blusoft.blucargo.gwt.client;

import java.util.List;

import com.blusoft.blucargo.model.CarBody;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CarBodyGWTService")
public interface CarBodyGWTService extends RemoteService {

	public void saveBodies(List<CarBody> bodies);

	public void saveBody(CarBody b);

	public List<CarBody> findAll();

	public CarBody findById(long id);

}
