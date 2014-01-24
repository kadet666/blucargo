package com.blusoft.blucargo.gwt.client;

import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CargoOfferGWTService")
public interface CargoOfferGWTService extends RemoteService {

	public void save(List<CargoOffer> offerList);

	public void save(CargoOffer co);

	public void removeCargoOffers(List<CargoOffer> cargoOffers);

	public void removeCargoOffer(CargoOffer cargoOffer);

	public List<CargoOffer> findAll();

	public List<CargoOffer> findAllThatAreNotAcceptedNorDeleted();

	public CargoOffer findById(long id);

	public CargoOffer findOfferById(long id);

}
