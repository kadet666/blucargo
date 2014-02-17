package com.blusoft.blucargo.services.impl;

import java.util.List;

import com.blusoft.blucargo.dao.BaseDao;

public class CommonServiceImpl<T> {

	protected BaseDao baseDao;

	public CommonServiceImpl(BaseDao dao) {
		baseDao = dao;
	}

	public void save(T t) {
		baseDao.saveOrUpdate(t);
	}

	public List<T> findAll() {
		return baseDao.findAll();
	}

	public List<T> findById(Long id) {
		return (List<T>) baseDao.findById(id);
	}

}
