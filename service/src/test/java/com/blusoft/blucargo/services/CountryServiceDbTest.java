//package com.blusoft.blucargo.services;
//
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.blusoft.blucargo.model.Country;
//import com.blusoft.blucargo.services.CountryService;
//
//public class CountryServiceDbTest extends BaseServiceTest {
//
//	@Autowired
//	CountryService countryService;
//
//	@Before
//	public void setUp() {
//
//	}
//
//	@Test
//	public void testGetCountries() {
//		Country country = new Country();
//		country.setIso_3166_1_alfa_2("aaa");
//
//		countryService.saveCountry(country);
//		assertTrue(countryService.findAll().size() > 0);
//
//	}
//
// }
