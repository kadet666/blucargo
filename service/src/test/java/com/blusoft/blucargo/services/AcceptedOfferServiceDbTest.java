//package com.blusoft.blucargo.services;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.blusoft.blucargo.model.AcceptedOffer;
//import com.blusoft.blucargo.model.CargoOffer;
//import com.blusoft.blucargo.services.AcceptedOfferService;
//import com.blusoft.blucargo.services.CargoOfferService;
//import com.blusoft.blucargo.services.OfferAcceptanceService;
//
//public class AcceptedOfferServiceDbTest extends BaseServiceTest {
//
//	@Autowired
//	OfferAcceptanceService offerAcceptanceService;
//	@Autowired
//	CargoOfferService cargoOfferService;
//	@Autowired
//	AcceptedOfferService acceptedOfferService;
//
//	@Before
//	public void setUp() {
//	}
//
//	@Test
//	public void testGetAcceptedOfferByCargoOfferAndUserName() {
//		CargoOffer co = new CargoOffer();
//		co.setOwner("a");
//		cargoOfferService.save(co);
//
//		AcceptedOffer ao = new AcceptedOffer();
//		ao.setOfferId(co.getId());
//		ao.setUserName("b");
//		acceptedOfferService.save(ao);
//
//		AcceptedOffer acceptedOfferBack = acceptedOfferService.getAcceptedOfferByCargoOfferIdAndUserName(co.getId(), "b");
//		assertEquals(acceptedOfferBack.getUserName(), "b");
//		assertEquals(acceptedOfferBack.getOfferId(), co.getId());
//	}
// }
