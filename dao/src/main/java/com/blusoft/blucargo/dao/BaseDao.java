package com.blusoft.blucargo.dao;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao<T> {

	public void delete(T entity);

	public List<T> findAll();

	public T findById(Long id);

	public void saveOrUpdate(T entity);

	// public EntityManager getEntityManager();
	public Session getSession();

}
