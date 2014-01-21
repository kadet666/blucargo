package com.blusoft.blucargo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.model.SearchCriteria;

@Repository("searchCriteria")
public interface SearchCriteriaDao extends BaseDao<SearchCriteria> {

	public List<SearchCriteria> findByUserName(String userName);

	public void removeById(Long id);

	public List<SearchCriteria> findByUserNameAndCriteriaName(String userName, String name);

	public void deleteByUserNameAndCriteriaName(String userName, String name);

	public void deleteByUser(String userName);
}
