package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/blucargo-dao-test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AbstractDAOTest {

	@Test
	public void shouldObjectFactoryBeNotNull() {
		assertNotNull(new Long(1L));
	}

}
