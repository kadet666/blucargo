package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.AcceptedOffer;
import com.blusoft.blucargo.model.CargoOffer;

public interface AcceptedOfferDao extends BaseDao<AcceptedOffer> {

	public void removeAcceptedOfferByCargoOfferAndOwner(CargoOffer co, String owner);

	public List<CargoOffer> getAcceptedCargoOffersByOwner(String owner);

	public AcceptedOffer getAcceptedOfferByCargoOfferIdAndUserName(Long cargoOfferId, String userName);

	public void delete(AcceptedOffer entity);

}
