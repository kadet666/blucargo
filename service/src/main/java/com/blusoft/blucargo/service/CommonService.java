package com.blusoft.blucargo.service;

import java.util.List;

public interface CommonService<T> {

	public void save(T t);

	public List<T> findAll();

	public List<T> findById(Long id);

}
