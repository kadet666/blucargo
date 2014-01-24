package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.EndedOfferDao;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.EndedOffer;
import com.blusoft.blucargo.service.EndedOfferService;

@Transactional
@Service("EndedOfferService")
public class EndedOfferServiceImpl implements EndedOfferService {

	@Autowired
	private EndedOfferDao endedOfferDao;

	public synchronized void saveEndedOffers(List<EndedOffer> endedOffers) {
		for (EndedOffer c : endedOffers) {
			save(c);
		}
	}

	public synchronized void save(EndedOffer ao) {
		EndedOffer endedOffer = endedOfferDao.findByOfferIdAndUserName(ao.getOfferId(), ao.getUserName());

		if (endedOffer == null) {
			endedOfferDao.saveOrUpdate(ao);
		}
	}

	public synchronized void removeEndedOffer(EndedOffer ao) {
		endedOfferDao.removeEndedOffer(ao);
	}

	public synchronized void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
		endedOfferDao.removeEndedOfferByCargoOfferAndOwner(co, owner);
	}

	public synchronized void removeEndedOffersByCargoOfferAndOwner(List<CargoOffer> offers, String owner) {
		for (CargoOffer co : offers) {
			this.removeEndedOfferByCargoOfferAndOwner(co, owner);
		}
	}

	public synchronized List<EndedOffer> findAll() {
		return endedOfferDao.findAll();
	}

	public synchronized EndedOffer findById(long id) {
		return endedOfferDao.findById(id);
	}

	public synchronized EndedOffer findByOfferIdAndUserName(long offerId, String userName) {
		return endedOfferDao.findByOfferIdAndUserName(offerId, userName);
	}

	public synchronized List<CargoOffer> getEndedCargoOffersByOwner(String owner) {
		return endedOfferDao.getEndedCargoOffersByOwner(owner);
	}

	public List<EndedOffer> findEndedOfferByOwner(String owner) {
		return this.endedOfferDao.findEndedOfferByOwner(owner);
	}

}
