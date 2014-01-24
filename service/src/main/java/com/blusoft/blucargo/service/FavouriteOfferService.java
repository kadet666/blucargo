package com.blusoft.blucargo.service;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.FavouriteOffer;

public interface FavouriteOfferService {

	public void saveFavouriteOffers(List<FavouriteOffer> favouriteOffers);

	public void saveFavouriteOffer(FavouriteOffer ao);

	public List<FavouriteOffer> findAll();

	public FavouriteOffer findById(long id);

	public void removeFavouriteOfferByCargoOfferAndOwner(CargoOffer co, String owner);

	public void removeFavouriteOffersByCargoOfferAndOwner(List<CargoOffer> offers, String owner);

	public List<CargoOffer> getFavouriteCargoOffersByOwner(String owner);
}
