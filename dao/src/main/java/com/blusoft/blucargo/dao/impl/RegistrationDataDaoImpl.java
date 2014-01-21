package com.blusoft.blucargo.dao.impl;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.RegistrationDataDao;
import com.blusoft.blucargo.model.RegistrationData;

@Repository("registrationData")
public class RegistrationDataDaoImpl extends BaseDaoImpl<RegistrationData> implements RegistrationDataDao {

	protected RegistrationDataDaoImpl() {
		super(RegistrationData.class);
	}

	public RegistrationData getUserByRegistrationNumber(String number) {
		String queryString = "SELECT c from RegistrationData c where c.regId=:regNumber";
		Query query = getSession().createQuery(queryString);
		query.setParameter("regNumber", number);
		return (RegistrationData) query.list();
	}

	public boolean checkLoginInTable(String login) {
		String queryString = "SELECT c from RegistrationData c where c.userName=:login";
		Query query = getSession().createQuery(queryString);
		query.setParameter("login", login);
		if (query.list().size() > 0)
			return true;
		else
			return false;
	}

	public void deleteAllByRegId(String string) {
		Query query = getSession().createQuery("DELETE FROM RegistrationData data where data.regId=:id");
		query.setParameter("id", string);
		query.executeUpdate();
	}

}
