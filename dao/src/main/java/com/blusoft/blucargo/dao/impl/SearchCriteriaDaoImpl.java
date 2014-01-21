package com.blusoft.blucargo.dao.impl;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.SearchCriteriaDao;
import com.blusoft.blucargo.model.SearchCriteria;

@Repository("searchCriteria")
public class SearchCriteriaDaoImpl extends BaseDaoImpl<SearchCriteria> implements SearchCriteriaDao {

	protected SearchCriteriaDaoImpl() {
		super(SearchCriteria.class);
	}

	public List<SearchCriteria> findByUserName(String userName) {
		Query q = getSession().createQuery("SELECT criteria FROM SearchCriteria criteria where criteria.userName=:userName ");
		q.setParameter("userName", userName);
		List<SearchCriteria> searchCriteriaList = q.list();
		return searchCriteriaList;
	}

	public void removeById(Long id) {
		Query q = getSession().createQuery("DELETE FROM SearchCriteria criteria where criteria.id=:id ");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public List<SearchCriteria> findByUserNameAndCriteriaName(String userName, String name) {
		Query q = getSession().createQuery("SELECT criteria FROM SearchCriteria criteria where criteria.userName=:userName and criteria.criteriaName=:name");
		q.setParameter("userName", userName);
		q.setParameter("name", name);
		List<SearchCriteria> searchCriteriaList = q.list();
		return searchCriteriaList;
	}

	public void deleteByUserNameAndCriteriaName(String userName, String name) {
		Query q = getSession().createQuery("delete FROM SearchCriteria criteria where criteria.userName=:userName and criteria.criteriaName=:name");
		q.setParameter("userName", userName);
		q.setParameter("name", name);
		q.executeUpdate();
	}

	public void deleteByUser(String userName) {
		Query q = getSession().createQuery("delete FROM SearchCriteria criteria where criteria.userName=:userName");
		q.setParameter("userName", userName);
		q.executeUpdate();
	}
}
