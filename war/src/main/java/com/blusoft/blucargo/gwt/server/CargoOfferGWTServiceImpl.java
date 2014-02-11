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

		CargoOffer o1 = new CargoOffer();
		o1.setCityFrom("111");
		o1.setCityTo("111");
		o1.setAddressFrom("1111");
		o1.setAddressTo("aoeaoe");
		o1.setCountryFrom("1aoeaoe");
		o1.setCountryTo("oeuoeu");
		o1.setPostOfficeFrom("oeuoeuoe");
		o1.setPostOfficeTo("oeuoeuoe");
		o1.setContact("oeuoeuoeu");

		CargoOffer o2 = new CargoOffer();
		o2.setCityFrom("111");
		o2.setCityTo("111");
		o2.setAddressFrom("1111");
		o2.setAddressTo("aoeaoe");
		o2.setCountryFrom("1aoeaoe");
		o2.setCountryTo("oeuoeu");
		o2.setPostOfficeFrom("oeuoeuoe");
		o2.setPostOfficeTo("oeuoeuoe");
		o2.setContact("oeuoeuoeu");

		CargoOffer o3 = new CargoOffer();
		o3.setCityFrom("111");
		o3.setCityTo("111");
		o3.setAddressFrom("1111");
		o3.setAddressTo("aoeaoe");
		o3.setCountryFrom("1aoeaoe");
		o3.setCountryTo("oeuoeu");
		o3.setPostOfficeFrom("oeuoeuoe");
		o3.setPostOfficeTo("oeuoeuoe");
		o3.setContact("oeuoeuoeu");

		// return Arrays.asList(o1, o2);

		return getCargoOfferService().findAllThatAreNotAcceptedNorDeleted();
	}

	public CargoOffer findById(long id) {
		return getCargoOfferService().findById(id);
	}

	public CargoOffer findOfferById(long id) {
		return getCargoOfferService().findOfferById(id);
	}

}
