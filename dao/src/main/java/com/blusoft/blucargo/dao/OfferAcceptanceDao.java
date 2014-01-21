package com.blusoft.blucargo.dao;

import com.blusoft.blucargo.model.OfferAcceptance;

public interface OfferAcceptanceDao extends BaseDao<OfferAcceptance> {

	public void deleteByInitiator(String string);

	public OfferAcceptance find(String initiator, String initiated, long cargoOfferId);

	public void save(OfferAcceptance offer);
}
