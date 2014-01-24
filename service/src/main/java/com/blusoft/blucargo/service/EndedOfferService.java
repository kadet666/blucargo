package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.EndedOffer;

public interface EndedOfferService {

	public void saveEndedOffers(List<EndedOffer> endedOffers);

	public void save(EndedOffer ao);

	public void removeEndedOffer(EndedOffer ao);

	public void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner);

	public void removeEndedOffersByCargoOfferAndOwner(List<CargoOffer> offers, String owner);

	public List<EndedOffer> findAll();

	public EndedOffer findById(long id);

	public EndedOffer findByOfferIdAndUserName(long offerId, String userName);

	public List<CargoOffer> getEndedCargoOffersByOwner(String owner);

	public List<EndedOffer> findEndedOfferByOwner(String owner);

}
