package com.blusoft.blucargo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.OfferAcceptanceDao;
import com.blusoft.blucargo.model.OfferAcceptance;

@Repository("offerAcceptance")
public class OfferAcceptanceDaoImpl extends BaseDaoImpl<OfferAcceptance> implements OfferAcceptanceDao {

	protected OfferAcceptanceDaoImpl() {
		super(OfferAcceptance.class);
	}

	public void deleteByInitiator(String string) {
		Query query = getSession().createQuery("DELETE FROM OfferAcceptance offer where offer.initiator=:initiator");
		query.setParameter("initiator", string);
		query.executeUpdate();
	}

	public OfferAcceptance find(String initiator, String initiated, long cargoOfferId) {

		String queryString = "SELECT e FROM " + persistentClass.getName() + " e where e.initiator=:initiator and e.initiated=:initiated and e.offerId=:offerId";
		Query query = getSession().createQuery(queryString);
		query.setParameter("initiator", initiator);
		query.setParameter("initiated", initiated);
		query.setParameter("offerId", cargoOfferId);
		List list = query.list();

		if (list.size() > 0) {
			return (OfferAcceptance) list.get(0);
		}
		return null;
	}

	public void save(OfferAcceptance offer) {
		Query query = getSession().createQuery("DELETE FROM OfferAcceptance offer where offer.initiator=:initiator and offer.offerId=:offerId");
		query.setParameter("initiator", offer.getInitiator());
		query.setParameter("offerId", offer.getOfferId());
		query.executeUpdate();
		this.saveOrUpdate(offer);

	}

}
