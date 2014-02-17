package com.blusoft.blucargo.dao.util;

import org.springframework.stereotype.Component;

import com.blusoft.blucargo.model.Dictionary;

@Component
public class ObjectFactory {

	private static final String VALUE = "value";
	private static final String LANGUAGE = "language";
	private static final String KEY = "key";

	public Dictionary getTransientDictionary() {

		Dictionary dictionary = new Dictionary();
		dictionary.setKey(KEY);
		dictionary.setLanguage(LANGUAGE);
		dictionary.setValue(VALUE);

		return dictionary;
	}

}
