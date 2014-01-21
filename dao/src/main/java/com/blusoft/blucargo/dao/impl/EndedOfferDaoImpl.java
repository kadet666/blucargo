package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.EndedOfferDao;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.EndedOffer;

@Repository("endedOffer")
public class EndedOfferDaoImpl extends BaseDaoImpl<EndedOffer> implements EndedOfferDao {

	protected EndedOfferDaoImpl() {
		super(EndedOffer.class);
	}

	public synchronized void removeEndedOfferByCargoOfferAndOwner(CargoOffer co, String owner) {
		Query q = getSession().createQuery("UPDATE EndedOffer o set o.visible=:visible where o.offerId=:cargoOfferId" + " and o.userName=:owner");
		q.setParameter("cargoOfferId", co.getId());
		q.setParameter("owner", owner);
		q.setParameter("visible", 0);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public synchronized List<CargoOffer> getEndedCargoOffersByOwner(String owner) {
		Query q = getSession().createQuery(
				"SELECT co FROM CargoOffer co where co.id in " + "(select ao.offerId from EndedOffer ao where ao.userName=:owner and ao.visible=1)");
		q.setParameter("owner", owner);
		List<CargoOffer> offerList = q.list();
		return offerList;
	}

	@SuppressWarnings("unchecked")
	public List<EndedOffer> findEndedOfferByOwner(String owner) {
		Query q = getSession().createQuery("SELECT ef FROM EndedOffer ef where ef.userName=:owner");
		q.setParameter("owner", owner);
		List<EndedOffer> offerList = (List<EndedOffer>) q.list();
		return offerList;
	}

	public EndedOffer findByOfferIdAndUserName(long offerId, String userName) {
		Query q = getSession().createQuery("SELECT eo from EndedOffer eo WHERE eo.offerId=:offerId and eo.userName=:userName");
		q.setParameter("offerId", offerId);
		q.setParameter("userName", userName);

		List<EndedOffer> endedOffer = q.list();

		if (endedOffer.size() > 0) {
			return endedOffer.get(0);
		}

		return null;
	}

	@Override
	public void removeEndedOffer(EndedOffer ao) {
		ao.setVisible(0);
		saveOrUpdate(ao);
	}

}
