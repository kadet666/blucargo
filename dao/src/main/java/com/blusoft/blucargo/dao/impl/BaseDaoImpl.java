package com.blusoft.blucargo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	protected Class<T> persistentClass;

	@Autowired
	protected SessionFactory sessionFactory;

	protected BaseDaoImpl(Class<T> persistentClass) {

		this.persistentClass = persistentClass;

	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String queryString = "SELECT e FROM " + persistentClass.getName() + " e";
		Query query = getSession().createQuery(queryString);
		return (List<T>) query.list();
	}

	public T findById(Long id) {
		return (T) getSession().get(persistentClass, id);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
