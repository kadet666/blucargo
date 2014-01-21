//package com.blusoft.blucargo.services;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.blusoft.blucargo.model.CargoOffer;
//import com.blusoft.blucargo.model.EndedOffer;
//import com.blusoft.blucargo.services.AcceptedOfferService;
//import com.blusoft.blucargo.services.CargoOfferService;
//import com.blusoft.blucargo.services.EndedOfferService;
//import com.blusoft.blucargo.services.OfferAcceptanceService;
//
//public class EndedOfferServiceDbTest extends BaseServiceTest {
//
//	@Autowired
//	OfferAcceptanceService offerAcceptanceService;
//	@Autowired
//	CargoOfferService cargoOfferService;
//	@Autowired
//	AcceptedOfferService acceptedOfferService;
//
//	@Autowired
//	EndedOfferService endedOfferService;
//
//	@Autowired
//	Session session;
//
//	@Before
//	public void setUp() {
//	}
//
//	@Test
//	public void testRemoveEndedOffer() {
//		CargoOffer co = new CargoOffer();
//		co.setOwner("b");
//		cargoOfferService.save(co);
//
//		EndedOffer endedOffer = new EndedOffer();
//		endedOffer.setUserName("a");
//		endedOffer.setOfferId(co.getId());
//		endedOfferService.save(endedOffer);
//
//		List<EndedOffer> endedOffers = endedOfferService.findAll();
//		assertEquals(endedOffers.get(0).getUserName(), "a");
//		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(), 1);
//
//		endedOfferService.removeEndedOffer(endedOffer);
//		List<EndedOffer> endedOffers2 = endedOfferService.findAll();
//		assertEquals(endedOffers2.get(0).getVisible(), new Integer(0));
//		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(), 0);
//	}
//
//	@Test
//	public void testRemoveEndedOfferByCargoOfferAndOwner() {
//		CargoOffer co = new CargoOffer();
//		co.setOwner("b");
//		cargoOfferService.save(co);
//
//		EndedOffer endedOffer = new EndedOffer();
//		endedOffer.setUserName("b");
//		endedOffer.setOfferId(co.getId());
//		endedOfferService.save(endedOffer);
//
//		List<EndedOffer> endedOffers = endedOfferService.findAll();
//
//		endedOfferService.removeEndedOfferByCargoOfferAndOwner(co, "a");
//		// endedOfferService.get
//
//		List<EndedOffer> endedOffers2 = endedOfferService.findAll();
//
//		assertEquals(endedOffers2.get(0).getUserName(), "b");
//		assertEquals(endedOfferService.getEndedCargoOffersByOwner("a").size(), 0);
//	}
//
//	@Test
//	public void testRemoveEndedOfferByCargoOfferAndOwner2() {
//		CargoOffer co = new CargoOffer();
//		co.setOwner("b");
//		cargoOfferService.save(co);
//
//		EndedOffer endedOffer = new EndedOffer();
//		endedOffer.setUserName("c");
//		endedOffer.setOfferId(co.getId());
//		endedOfferService.save(endedOffer);
//
//		endedOfferService.removeEndedOfferByCargoOfferAndOwner(co, "c");
//
//		assertEquals(endedOfferService.getEndedCargoOffersByOwner("c").size(), 0);
//
//	}
//
//	@Test
//	public void testForInsertingNonUniqueValue() {
//		EndedOffer endedOffer = new EndedOffer();
//		endedOffer.setOfferId(301L);
//		endedOffer.setUserName("a");
//
//		endedOfferService.save(endedOffer);
//		session.flush();
//
//		EndedOffer endedOffer2 = new EndedOffer();
//		endedOffer2.setOfferId(301L);
//		endedOffer2.setUserName("a");
//
//		endedOfferService.save(endedOffer2);
//		session.flush();
//	}
//
// }
