package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.AcceptedOffer;
import com.blusoft.blucargo.model.CargoOffer;

public class AcceptedOfferDaoDbTest extends AbstractDAOTest {

	@Autowired
	AcceptedOfferDao acceptedOfferDao;

	@Autowired
	CargoOfferDao cargoOfferDao;

	private CargoOffer co;
	private AcceptedOffer ao;

	@Before
	public void setUp() {
		co = new CargoOffer();
		co.setOwner("a");
		cargoOfferDao.saveOrUpdate(co);

		ao = new AcceptedOffer();
		ao.setOfferId(co.getId());
		ao.setUserName("b");
		acceptedOfferDao.saveOrUpdate(ao);
	}

	@Test
	public void testGetAcceptedCargoOffersByOwner() {
		List<AcceptedOffer> acceptedOffers = acceptedOfferDao.findAll();
		assertTrue(acceptedOffers.size() > 0);
		assertEquals(acceptedOffers.get(0).getUserName(), "b");
		assertEquals(acceptedOffers.get(0).getOfferId(), co.getId());
	}

	@Test
	public void testRemoveAcceptedCargoOffersByOwner() {

		acceptedOfferDao.removeAcceptedOfferByCargoOfferAndOwner(co, "b");
		List<AcceptedOffer> acceptedOffers = acceptedOfferDao.findAll();
		assertTrue(acceptedOffers.size() == 0);

	}

	@Test
	public void testRemoveAcceptedCargoOffersByOwner2() {

		acceptedOfferDao.removeAcceptedOfferByCargoOfferAndOwner(co, "b");
		List<AcceptedOffer> acceptedOffers = acceptedOfferDao.findAll();
		assertTrue(acceptedOffers.size() == 0);

	}

	@Test
	public void testGetAcceptedOfferByCargoOfferAndUserName() {
		CargoOffer co = new CargoOffer();
		co.setOwner("a");
		cargoOfferDao.saveOrUpdate(co);

		AcceptedOffer ao = new AcceptedOffer();
		ao.setOfferId(co.getId());
		ao.setUserName("b");
		acceptedOfferDao.saveOrUpdate(ao);

		AcceptedOffer acceptedOfferBack = acceptedOfferDao.getAcceptedOfferByCargoOfferIdAndUserName(co.getId(), "b");
		assertEquals(acceptedOfferBack.getUserName(), "b");
		assertEquals(acceptedOfferBack.getOfferId(), co.getId());
	}

}
