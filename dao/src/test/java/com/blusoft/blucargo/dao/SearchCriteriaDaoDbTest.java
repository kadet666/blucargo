package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.SearchCriteria;

public class SearchCriteriaDaoDbTest extends AbstractDAOTest {

	@Autowired
	SearchCriteriaDao criteriaDao;

	@Before
	public void setUp() {
	}

	@Test
	public void testSaveSearchCriteria() {

		SearchCriteria s = new SearchCriteria();
		s.setUserName("123");
		s.setValue("aaa");
		criteriaDao.saveOrUpdate(s);

		List<SearchCriteria> searchCriteriaList = criteriaDao.findAll();
		assertEquals(1, searchCriteriaList.size());

	}

	@Test
	public void testSaveSearchCriteriaWithValue() {
		SearchCriteria s = new SearchCriteria();

		s.setValue("aaa");
		s.setUserName("kris");
		s.setValue("{\"departureCountry\":\"Algieria\",\"departureCity\":\"Alger\",\"arrivalCountry\":\"Anguilla\",\"arrivalCity\":\"Long Path\",\"body\":\"Platforma\",\"weight\":\"k2\"}");

		criteriaDao.saveOrUpdate(s);
		assertTrue(criteriaDao.findAll().size() > 0);

	}

	@Test
	public void testFindByUser() {

		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setValue("bbb");

		criteriaDao.saveOrUpdate(c1);
		criteriaDao.saveOrUpdate(c2);

		List<SearchCriteria> list = criteriaDao.findByUserName("b");
		SearchCriteria cBack = list.get(0);

		assertEquals("bbb", cBack.getValue());

	}

	@Test
	public void testFindByUserAndName() {
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setCriteriaName("b1");
		c2.setValue("bbb");

		SearchCriteria c3 = new SearchCriteria();
		c3.setUserName("b");
		c3.setCriteriaName("b2");
		c3.setValue("bbb");

		criteriaDao.saveOrUpdate(c1);
		criteriaDao.saveOrUpdate(c2);
		criteriaDao.saveOrUpdate(c3);

		assertEquals(3, criteriaDao.findAll().size());

		List<SearchCriteria> list = criteriaDao.findAll();

		list = criteriaDao.findByUserNameAndCriteriaName("b", "b2");
		SearchCriteria cBack = list.get(0);

		assertEquals("bbb", cBack.getValue());
		assertEquals("b2", cBack.getCriteriaName());

	}

	@Test
	public void testDeleteByUser() {
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setCriteriaName("b1");
		c2.setValue("bbb");

		SearchCriteria c3 = new SearchCriteria();
		c3.setUserName("b");
		c3.setCriteriaName("b2");
		c3.setValue("bbb");

		criteriaDao.saveOrUpdate(c1);
		criteriaDao.saveOrUpdate(c2);
		criteriaDao.saveOrUpdate(c3);

		criteriaDao.deleteByUser("b");
		List<SearchCriteria> list = criteriaDao.findAll();

		assertEquals(1, list.size());
		assertEquals("aaa", list.get(0).getValue());

	}

	@Test
	public void testDeleteByUserAndName() {
		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setCriteriaName("b1");
		c2.setValue("bbb");

		SearchCriteria c3 = new SearchCriteria();
		c3.setUserName("b");
		c3.setCriteriaName("b2");
		c3.setValue("bbb");

		criteriaDao.saveOrUpdate(c1);
		criteriaDao.saveOrUpdate(c2);
		criteriaDao.saveOrUpdate(c3);

		criteriaDao.deleteByUserNameAndCriteriaName("b", "b2");
		List<SearchCriteria> list = criteriaDao.findAll();

		assertEquals(2, list.size());
		assertEquals("aaa", list.get(0).getValue());
		assertEquals("bbb", list.get(1).getValue());

	}

	@Test
	public void testRemove() {

		SearchCriteria c1 = new SearchCriteria();
		c1.setUserName("a");
		c1.setValue("aaa");

		SearchCriteria c2 = new SearchCriteria();
		c2.setUserName("b");
		c2.setValue("bbb");

		criteriaDao.saveOrUpdate(c1);
		criteriaDao.saveOrUpdate(c2);

		List<SearchCriteria> list = criteriaDao.findAll();
		assertEquals(2, list.size());

		criteriaDao.removeById(c1.getId());
		criteriaDao.removeById(c2.getId());

		list = criteriaDao.findAll();
		assertEquals(0, list.size());

	}

	@Test
	public void testFindAllReturnsEmptyCollection() {
		List<SearchCriteria> list = criteriaDao.findAll();

		assertNotNull(list);
		assertTrue(list.size() == 0);
	}

	@Test
	public void testFindByUserNameReturnsEmptyCollection() {
		List<SearchCriteria> list = criteriaDao.findByUserName("aaa");

		assertNotNull(list);
		assertTrue(list.size() == 0);
	}

}
