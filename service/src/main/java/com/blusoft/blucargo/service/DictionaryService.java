package com.blusoft.blucargo.service;

import com.blusoft.blucargo.model.Dictionary;

public interface DictionaryService extends CommonService<Dictionary> {

	Dictionary findByLangAndKey(String language, String key);

	Dictionary findByLangAndKey(String language, String key, String defaultExpression);

}
