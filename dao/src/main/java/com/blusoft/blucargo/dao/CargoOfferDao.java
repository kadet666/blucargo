package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;

public interface CargoOfferDao extends BaseDao<CargoOffer> {

	public abstract CargoOffer findOfferById(Long id);

	public List<CargoOffer> findAllThatAreNotAcceptedNorEnded();

}