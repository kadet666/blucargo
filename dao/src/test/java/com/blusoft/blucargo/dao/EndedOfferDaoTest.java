package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.EndedOffer;

public class EndedOfferDaoTest extends AbstractDAOTest {

	@Autowired
	OfferAcceptanceDao offerAcceptanceService;

	@Autowired
	CargoOfferDao cargoOfferService;

	@Autowired
	AcceptedOfferDao acceptedOfferService;

	@Autowired
	EndedOfferDao endedOfferService;

	@Before
	public void setUp() {
	}

	@Test
	public void testRemoveEndedOffer() {
		CargoOffer co = new CargoOffer();
		co.setOwner("b");
		cargoOfferService.saveOrUpdate(co);

		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setUserName("a");
		endedOffer.setOfferId(co.getId());
		endedOfferService.saveOrUpdate(endedOffer);

		List<EndedOffer> endedOffers = endedOfferService.findAll();
		assertEquals(endedOffers.get(0).getUserName(), "a");
		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(), 1);

		endedOfferService.removeEndedOffer(endedOffer);
		List<EndedOffer> endedOffers2 = endedOfferService.findAll();
		assertEquals(endedOffers2.get(0).getVisible(), new Integer(0));
		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(), 0);
	}

	@Test
	public void testRemoveEndedOfferByCargoOfferAndOwner() {
		CargoOffer co = new CargoOffer();
		co.setOwner("b");
		cargoOfferService.saveOrUpdate(co);

		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setUserName("b");
		endedOffer.setOfferId(co.getId());
		endedOfferService.saveOrUpdate(endedOffer);

		List<EndedOffer> endedOffers = endedOfferService.findAll();

		endedOfferService.removeEndedOfferByCargoOfferAndOwner(co, "a");
		// endedOfferService.get

		List<EndedOffer> endedOffers2 = endedOfferService.findAll();

		assertEquals(endedOffers2.get(0).getUserName(), "b");
		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(), 0);
	}

	@Test
	public void testRemoveEndedOfferByCargoOfferAndOwner2() {
		CargoOffer co = new CargoOffer();
		co.setOwner("b");
		cargoOfferService.saveOrUpdate(co);

		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setUserName("c");
		endedOffer.setOfferId(co.getId());
		endedOfferService.saveOrUpdate(endedOffer);

		endedOfferService.removeEndedOfferByCargoOfferAndOwner(co, "c");

		assertEquals(endedOfferService.getEndedCargoOffersByOwner("c").size(), 0);

	}

	@Test(expected = ConstraintViolationException.class)
	public void testForInsertingNonUniqueValue() {
		EndedOffer endedOffer = new EndedOffer();
		endedOffer.setOfferId(301L);
		endedOffer.setUserName("a");

		endedOfferService.saveOrUpdate(endedOffer);
		endedOfferService.getSession().flush();

		EndedOffer endedOffer2 = new EndedOffer();
		endedOffer2.setOfferId(301L);
		endedOffer2.setUserName("a");

		endedOfferService.saveOrUpdate(endedOffer2);
		endedOfferService.getSession().flush();
	}

}
