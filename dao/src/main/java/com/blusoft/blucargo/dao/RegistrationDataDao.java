package com.blusoft.blucargo.dao;

import com.blusoft.blucargo.model.RegistrationData;

public interface RegistrationDataDao extends BaseDao<RegistrationData> {

	abstract RegistrationData getUserByRegistrationNumber(String number);

	boolean checkLoginInTable(String login);

	void deleteAllByRegId(String string);

}
