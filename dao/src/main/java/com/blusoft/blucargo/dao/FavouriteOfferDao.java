package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.FavouriteOffer;

public interface FavouriteOfferDao extends BaseDao<FavouriteOffer> {

	public void removeFavouriteOfferByCargoOfferAndOwner(CargoOffer co, String owner);

	public List<CargoOffer> getFavouriteCargoOffersByOwner(String owner);

}
