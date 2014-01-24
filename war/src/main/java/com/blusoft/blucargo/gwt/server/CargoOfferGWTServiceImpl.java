package com.blusoft.blucargo.gwt.server;

import java.util.List;

import com.blusoft.blucargo.gwt.client.CargoOfferGWTService;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.service.CargoOfferService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CargoOfferGWTServiceImpl extends RemoteServiceServlet implements CargoOfferGWTService {

	private CargoOfferService cargoOfferService;

	public CargoOfferService getCargoOfferService() {
		if (cargoOfferService == null) {
			cargoOfferService = (CargoOfferService) SpringHelper.getBean("CargoOfferService", getServletContext());
		}

		return cargoOfferService;
	}

	// public CargoOfferGWTServiceImpl() {
	// }

	public void save(List<CargoOffer> offerList) {
		getCargoOfferService().save(offerList);
	}

	public void save(CargoOffer co) {
		getCargoOfferService().save(co);
	}

	public void removeCargoOffers(List<CargoOffer> cargoOffers) {
		getCargoOfferService().removeCargoOffers(cargoOffers);

	}

	public void removeCargoOffer(CargoOffer cargoOffer) {
		getCargoOfferService().removeCargoOffer(cargoOffer);
	}

	public List<CargoOffer> findAll() {
		return getCargoOfferService().findAll();
	}

	public List<CargoOffer> findAllThatAreNotAcceptedNorDeleted() {
		return getCargoOfferService().findAllThatAreNotAcceptedNorDeleted();
	}

	public CargoOffer findById(long id) {
		return getCargoOfferService().findById(id);
	}

	public CargoOffer findOfferById(long id) {
		return getCargoOfferService().findOfferById(id);
	}

}
