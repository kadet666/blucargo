package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.RegistrationDataDao;
import com.blusoft.blucargo.model.RegistrationData;
import com.blusoft.blucargo.services.RegistrationDataService;

@Transactional
@Service("RegistrationDataService")
public class RegistrationDataServiceImpl implements RegistrationDataService {

	@Autowired
	private RegistrationDataDao registrationDataDao;

	public synchronized void save(List<RegistrationData> regData) {
		for (RegistrationData rd : regData) {
			registrationDataDao.saveOrUpdate(rd);
		}
	}

	public synchronized void save(RegistrationData regData) {
		this.registrationDataDao.saveOrUpdate(regData);
	}

	public RegistrationData getUserByRegistrationNumber(String number) {
		return registrationDataDao.getUserByRegistrationNumber(number);
	}

	public boolean checkLoginInTable(String login) {
		return registrationDataDao.checkLoginInTable(login);
	}

	public void deleteAllByRegId(String string) {
		registrationDataDao.deleteAllByRegId(string);
	}
}
