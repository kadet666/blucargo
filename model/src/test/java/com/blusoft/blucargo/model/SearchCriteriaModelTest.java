package com.blusoft.blucargo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blusoft.blucargo.model.SearchCriteria;

public class SearchCriteriaModelTest {

	@Test
	public void testValueField() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setValue("123qwe");
		assertEquals("123qwe", searchCriteria.getValue());
	}

	@Test
	public void testUserNameField() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setUserName("123qwe");
		assertEquals("123qwe", searchCriteria.getUserName());
	}

}
