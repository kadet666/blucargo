package com.blusoft.blucargo.service;

import java.util.List;

import com.blusoft.blucargo.model.RegistrationData;

public interface RegistrationDataService {

	public void save(List<RegistrationData> regData);

	public void save(RegistrationData regData);

	public RegistrationData getUserByRegistrationNumber(String number);

	public boolean checkLoginInTable(String login);

	public void deleteAllByRegId(String string);

}
