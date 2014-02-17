package com.blusoft.blucargo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.dao.DictionaryDao;
import com.blusoft.blucargo.model.Dictionary;
import com.blusoft.blucargo.service.DictionaryService;

public class DictionaryServiceImpl extends CommonServiceImpl<Dictionary> implements DictionaryService {

	DictionaryDao dictionaryDao;

	@Autowired
	public DictionaryServiceImpl(DictionaryDao dao) {
		super(dao);
		dictionaryDao = dao;
	}

	@Override
	public Dictionary findByLangAndKey(String language, String key) {
		return dictionaryDao.findByLangAndKey(language, key);
	}

	@Override
	public Dictionary findByLangAndKey(String language, String key, String defaultExpression) {
		return dictionaryDao.findByLangAndKey(language, key, defaultExpression);
	}

}
