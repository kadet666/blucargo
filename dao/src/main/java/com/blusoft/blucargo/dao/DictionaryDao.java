package com.blusoft.blucargo.dao;

import com.blusoft.blucargo.model.Dictionary;

public interface DictionaryDao extends BaseDao<Dictionary> {

	Dictionary findByLangAndKey(String language, String key);

	Dictionary findByLangAndKey(String language, String key, String defaultExpression);

}
