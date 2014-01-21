package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.CargoOfferDao;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.services.CargoOfferService;

@Transactional
@Service("CargoOfferService")
public class CargoOfferServiceImpl implements CargoOfferService {

	@Autowired
	private CargoOfferDao cargoOfferDao;

	public synchronized void save(List<CargoOffer> offerList) {
		for (CargoOffer co : offerList) {
			cargoOfferDao.saveOrUpdate(co);
		}
	}

	public CargoOfferDao getCargoOfferDao() {
		return cargoOfferDao;
	}

	public synchronized void save(CargoOffer co) {
		cargoOfferDao.saveOrUpdate(co);
	}

	public synchronized void removeCargoOffers(List<CargoOffer> cargoOffers) {
		for (CargoOffer co : cargoOffers) {
			this.removeCargoOffer(co);
		}
	}

	public synchronized void removeCargoOffer(CargoOffer cargoOffer) {
		cargoOfferDao.delete(cargoOffer);

	}

	public synchronized List<CargoOffer> findAll() {
		return cargoOfferDao.findAll();
	}

	public synchronized List<CargoOffer> findAllThatAreNotAcceptedNorDeleted() {
		return cargoOfferDao.findAllThatAreNotAcceptedNorEnded();
	}

	public synchronized CargoOffer findById(long id) {
		return cargoOfferDao.findById(id);
	}

	public synchronized CargoOffer findOfferById(long id) {
		return cargoOfferDao.findOfferById(id);
	}

}
