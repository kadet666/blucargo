package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.SearchCriteriaDao;
import com.blusoft.blucargo.model.SearchCriteria;
import com.blusoft.blucargo.services.SearchCriteriaService;

@Transactional
@Service("SearchCriteriaService")
public class SearchCriteriaServiceImpl implements SearchCriteriaService {

	@Autowired
	private SearchCriteriaDao searchCriteriaDao;

	public synchronized void save(List<SearchCriteria> searchCriterias) {
		for (SearchCriteria s : searchCriterias) {
			save(s);
		}
	}

	public synchronized void save(SearchCriteria c) {
		searchCriteriaDao.saveOrUpdate(c);
	}

	public synchronized List<SearchCriteria> findAll() {
		return searchCriteriaDao.findAll();
	}

	public synchronized SearchCriteria findById(long id) {
		return searchCriteriaDao.findById(id);
	}

	public List<SearchCriteria> findByUserName(String string) {
		return searchCriteriaDao.findByUserName(string);
	}

	public void removeById(Long id) {
		searchCriteriaDao.removeById(id);
	}

	public List<SearchCriteria> findByUserNameAndCriteriaName(String userName, String name) {
		return searchCriteriaDao.findByUserNameAndCriteriaName(userName, name);
	}

	public void deleteByUserNameAndCriteriaName(String userName, String name) {
		searchCriteriaDao.deleteByUserNameAndCriteriaName(userName, name);
	}

	public void deleteByUser(String string) {
		searchCriteriaDao.deleteByUser(string);
	}

}
