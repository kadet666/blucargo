package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.EndedOffer;

public interface EndedOfferDao extends BaseDao<EndedOffer> {

	List<CargoOffer> getEndedCargoOffersByOwner(String owner);

	List<EndedOffer> findEndedOfferByOwner(String owner);

	EndedOffer findByOfferIdAndUserName(long offerId, String userName);

	void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner);

	void removeEndedOffer(EndedOffer ao);

}
