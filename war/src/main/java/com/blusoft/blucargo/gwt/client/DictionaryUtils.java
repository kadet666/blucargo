package com.blusoft.blucargo.gwt.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.i18n.client.LocaleInfo;

public class DictionaryUtils implements DictionaryContstants {

	private static Map<String, Dictionary> I18N_DICTIONARIES = new HashMap<String, Dictionary>();

	private static Logger LOGGER = Logger.getLogger(DictionaryUtils.class);

	private static Dictionary createDictionary(String dictionaryName) {

		String moduleId = null;

		if (DEFAULT.equals(LocaleInfo.getCurrentLocale().getLocaleName())) {
			moduleId = dictionaryName + ".properties";
		} else {
			moduleId = dictionaryName + "_" + LocaleInfo.getCurrentLocale().getLocaleName() + ".properties";
		}

		Dictionary dictionary = Dictionary.getDictionary(moduleId);
		I18N_DICTIONARIES.put(dictionaryName, dictionary);
		return dictionary;

	}

	public static String getI18NString(String dictionaryName, String stringToInternationalize) {

		Dictionary dictionary = I18N_DICTIONARIES.get(dictionaryName);
		if (dictionary == null) {
			dictionary = createDictionary(dictionaryName);
		}
		String i18string = null;
		if (dictionary == null)
			return stringToInternationalize;
		try {
			i18string = dictionary.get(stringToInternationalize);
		} catch (Exception e) {

			LOGGER.error(e);

		}
		return i18string;
	}

}
