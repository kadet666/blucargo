package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.OfferAcceptance;

public interface OfferAcceptanceService {

	public void save(List<OfferAcceptance> offerList);

	public void save(OfferAcceptance offer);

	public void remove(List<OfferAcceptance> offerList);

	public void removeOfferAcceptance(OfferAcceptance offerAcceptance);

	public List<OfferAcceptance> findAll();

	public OfferAcceptance findById(long id);

	public void initiatorLogsOut(String string);

	public void initiateOffer(String initiator, String initiated, CargoOffer co);
}
