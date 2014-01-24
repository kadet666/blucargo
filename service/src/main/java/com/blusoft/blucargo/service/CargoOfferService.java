package com.blusoft.blucargo.service;

import java.util.List;

import com.blusoft.blucargo.dao.CargoOfferDao;
import com.blusoft.blucargo.model.CargoOffer;

public interface CargoOfferService {

	public void save(List<CargoOffer> offerList);

	public CargoOfferDao getCargoOfferDao();

	public void save(CargoOffer co);

	public void removeCargoOffers(List<CargoOffer> cargoOffers);

	public void removeCargoOffer(CargoOffer cargoOffer);

	public List<CargoOffer> findAll();

	public List<CargoOffer> findAllThatAreNotAcceptedNorDeleted();

	public CargoOffer findById(long id);

	public CargoOffer findOfferById(long id);

}
