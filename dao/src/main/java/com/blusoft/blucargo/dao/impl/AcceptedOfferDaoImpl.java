package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.AcceptedOfferDao;
import com.blusoft.blucargo.model.AcceptedOffer;
import com.blusoft.blucargo.model.CargoOffer;

@Repository("acceptedOffer")
public class AcceptedOfferDaoImpl extends BaseDaoImpl<AcceptedOffer> implements AcceptedOfferDao {

	protected AcceptedOfferDaoImpl() {
		super(AcceptedOffer.class);
	}

	public synchronized void removeAcceptedOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
		Query q = getSession().createQuery("delete from AcceptedOffer ao where ao.offerId=:cargoOfferId" + " and ao.userName=:owner");
		q.setParameter("cargoOfferId", co.getId());
		q.setParameter("owner", owner);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getAcceptedCargoOffersByOwner(String owner) {
		Query q = getSession().createQuery(
				"SELECT co FROM CargoOffer co where co.id in " + "(select ao.offerId from AcceptedOffer ao where ao.userName=:owner)");
		q.setParameter("owner", owner);
		List<CargoOffer> offerList = q.list();
		return offerList;
	}

	public AcceptedOffer getAcceptedOfferByCargoOfferIdAndUserName(Long cargoOfferId, String userName) {
		Query q = getSession().createQuery("SELECT ao FROM AcceptedOffer ao where ao.offerId=:cargoOfferId and ao.userName=:userName");
		q.setParameter("cargoOfferId", cargoOfferId);
		q.setParameter("userName", userName);
		List<AcceptedOffer> acceptedOffers = q.list();

		if (acceptedOffers.size() > 0) {
			return acceptedOffers.get(0);
		}

		return null;
	}

}
