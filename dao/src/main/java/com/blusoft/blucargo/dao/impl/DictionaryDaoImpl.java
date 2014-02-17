package com.blusoft.blucargo.dao.impl;

import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.DictionaryDao;
import com.blusoft.blucargo.model.Dictionary;

@Repository("dictionary")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements DictionaryDao {

	protected DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	@Override
	public Dictionary findByLangAndKey(String language, String key) {
		org.hibernate.Query query = getSession().createQuery("from Dictionary where language = :language and key = :key");
		query.setParameter("language", language);
		query.setParameter("key", key);

		Object obj = query.uniqueResult();

		if (null != obj) {
			return (Dictionary) obj;
		}

		return null;
	}

	@Override
	public Dictionary findByLangAndKey(String language, String key, String defaultValue) {

		Dictionary dict = findByLangAndKey(language, key);

		if (null != dict) {
			return dict;
		}

		Dictionary newDictionary = new Dictionary();
		newDictionary.setKey(key);
		newDictionary.setLanguage(language);
		newDictionary.setValue(defaultValue);

		saveOrUpdate(newDictionary);

		return newDictionary;

	}

}
