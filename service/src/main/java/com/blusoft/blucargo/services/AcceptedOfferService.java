package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.AcceptedOffer;
import com.blusoft.blucargo.model.CargoOffer;

public interface AcceptedOfferService {

	public void saveAcceptedOffers(List<AcceptedOffer> acceptedOffers);

	public void save(AcceptedOffer ao);

	public void removeAcceptedOffer(AcceptedOffer ao);

	public void removeAcceptedOffers(List<AcceptedOffer> list);

	public void removeAcceptedOfferByCargoOfferAndOwner(CargoOffer co, String owner);

	public void removeAcceptedOffersByCargoOfferAndOwner(List<CargoOffer> offers, String owner);

	public List<AcceptedOffer> findAll();

	public AcceptedOffer findById(long id);

	public List<CargoOffer> getAcceptedCargoOffersByOwner(String owner);

	public AcceptedOffer getAcceptedOfferByCargoOfferIdAndUserName(Long cargoOfferId, String userName);

}
