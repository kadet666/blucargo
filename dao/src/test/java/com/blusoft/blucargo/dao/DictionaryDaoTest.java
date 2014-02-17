package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.dao.util.ObjectFactory;
import com.blusoft.blucargo.model.Dictionary;

public class DictionaryDaoTest extends AbstractDAOTest {

	private static final String DEFAULT_VALUE = "defaultValue";

	private static final String SOME_VALUE = "someValue";

	private static final String SOME_KEY = "someKey";

	@Autowired
	private DictionaryDao dictionaryDao;

	@Autowired
	ObjectFactory objectFactory;

	@Test
	public void shouldSaveDictionary() {

		Dictionary dictionary = objectFactory.getTransientDictionary();
		dictionaryDao.saveOrUpdate(dictionary);

		List<Dictionary> dictionaries = dictionaryDao.findAll();
		assertTrue(dictionaries.size() > 0);

	}

	@Test
	public void shouldFindyByLangAndKey() {

		Dictionary dictionary = objectFactory.getTransientDictionary();
		dictionaryDao.saveOrUpdate(dictionary);

		Dictionary dictionaryBack = dictionaryDao.findByLangAndKey(dictionary.getLanguage(), dictionary.getKey());
		assertEquals(dictionary.getValue(), dictionaryBack.getValue());

	}

	@Test
	public void shouldFindyByLangAndKeyWithDefaultValue() {

		Dictionary dictionaryBack = dictionaryDao.findByLangAndKey(SOME_KEY, SOME_VALUE, DEFAULT_VALUE);
		assertEquals(DEFAULT_VALUE, dictionaryBack.getValue());

	}

}
