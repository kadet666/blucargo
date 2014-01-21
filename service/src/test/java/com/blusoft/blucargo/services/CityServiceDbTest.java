//package com.blusoft.blucargo.services;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//
//import com.blusoft.blucargo.model.City;
//
//public class CityServiceDbTest extends BaseServiceTest {
//
//	// @Autowired
//	CityService cityService;
//
//	@Autowired
//	protected LocalSessionFactoryBean sessionFactory;
//
//	@Before
//	public void setUp() {
//
//	}
//
//	@Test
//	public void testGetCitiesByCountry() {
//		City c1 = new City();
//		c1.setCity("aaaa");
//		c1.setCountry("pl");
//
//		City c2 = new City();
//		c2.setCity("bbbb");
//		c2.setCountry("pl");
//
//		City c3 = new City();
//		c3.setCity("cccc");
//		c3.setCountry("be");
//
//		cityService.saveCity(c1);
//		cityService.saveCity(c2);
//		cityService.saveCity(c3);
//
//		List<City> cities = cityService.findAll();
//
//		assertEquals(cityService.findCitiesByCountry("pl").size(), 2);
//
//	}
//
// }
