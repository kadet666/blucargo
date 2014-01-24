package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.SearchCriteria;

public interface SearchCriteriaService {

	public void save(List<SearchCriteria> searchCriterias);

	public void save(SearchCriteria c);

	public List<SearchCriteria> findAll();

	public SearchCriteria findById(long id);

	public List<SearchCriteria> findByUserName(String string);

	public void removeById(Long id);

	public List<SearchCriteria> findByUserNameAndCriteriaName(String userName, String name);

	public void deleteByUserNameAndCriteriaName(String userName, String name);

	public void deleteByUser(String string);

}
