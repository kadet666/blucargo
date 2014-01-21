package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.CargoOfferDao;
import com.blusoft.blucargo.model.CargoOffer;

@Repository("cargoOffer")
public class CargoOfferDaoImpl extends BaseDaoImpl<CargoOffer> implements CargoOfferDao {

	protected CargoOfferDaoImpl() {
		super(CargoOffer.class);
	}

	@SuppressWarnings("unchecked")
	public List<CargoOffer> findAllThatAreNotAcceptedNorEnded() {
		String queryString = "SELECT c from CargoOffer c where c.id not in" + "(select ao.offerId from AcceptedOffer ao) and c.id not in"
				+ "(select eo.offerId from EndedOffer eo)";
		Query query = getSession().createQuery(queryString);
		return (List<CargoOffer>) query.list();
	}

	public CargoOffer findOfferById(Long id) {
		String queryString = "SELECT c from CargoOffer c where c.id=:offerId";
		Query query = getSession().createQuery(queryString);
		query.setParameter("offerId", id);
		return (CargoOffer) query.list();
	}
}
