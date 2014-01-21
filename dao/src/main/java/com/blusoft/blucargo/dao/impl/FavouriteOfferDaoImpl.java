package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.FavouriteOfferDao;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.FavouriteOffer;

@Repository("favouriteOffer")
public class FavouriteOfferDaoImpl extends BaseDaoImpl<FavouriteOffer> implements FavouriteOfferDao {

	protected FavouriteOfferDaoImpl() {
		super(FavouriteOffer.class);
	}

	public synchronized void removeFavouriteOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
		Query q = getSession().createQuery("delete from FavouriteOffer ao where ao.offerId=:cargoOfferId" + " and ao.userName=:owner");
		q.setParameter("cargoOfferId", co.getId());
		q.setParameter("owner", owner);
		@SuppressWarnings("unused")
		int result = q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getFavouriteCargoOffersByOwner(String owner) {
		Query q = getSession().createQuery(
				"SELECT co FROM CargoOffer co where co.id in " + "(select fo.offerId from FavouriteOffer fo where fo.userName=:owner)");
		q.setParameter("owner", owner);
		List<CargoOffer> offerList = q.list();
		return offerList;
	}

}
