//package com.blusoft.blucargo.dao;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.blusoft.blucargo.model.AcceptedOffer;
//import com.blusoft.blucargo.model.CargoOffer;
//import com.blusoft.blucargo.model.OfferAcceptance;
//
//public class OfferAcceptanceDbTest {
//
//	@Autowired
//	OfferAcceptanceDao offerAcceptanceService;
//	@Autowired
//	CargoOfferDao cargoOfferService;
//	@Autowired
//	AcceptedOfferDao acceptedOfferService;
//
//	@Test
//	public void testCreateOfferAcceptance() {
//		OfferAcceptance offer = new OfferAcceptance();
//		offer.setInitiator("a");
//		offer.setInitiated("b");
//		offerAcceptanceService.save(offer);
//
//		OfferAcceptance offer2 = offerAcceptanceService.findAll().get(0);
//		assertEquals(offer2.getInitiator(), "a");
//		assertEquals(offer2.getInitiated(), "b");
//
//	}
//
//
//
//	@Test
//	public void testInitializeOffer() {
//		CargoOffer co = new CargoOffer();
//		co.setAddressFrom("Opole");
//		cargoOfferService.save(co);
//
//		assertEquals(0, offerAcceptanceService.findAll().size());
//		offerAcceptanceService.initiateOffer("a", "b", co);
//
//		List<OfferAcceptance> offerList = offerAcceptanceService.findAll();
//		assertEquals(1, offerList.size());
//		assertEquals("a", offerList.get(0).getInitiator());
//		assertEquals("b", offerList.get(0).getInitiated());
//		assertEquals(co.getId(), offerList.get(0).getOfferId());
//	}
//
//	@Test
//	public void testInitializeOffer2() {
//		CargoOffer co = new CargoOffer();
//		co.setAddressFrom("Opole");
//		cargoOfferService.save(co);
//
//		assertEquals(0, offerAcceptanceService.findAll().size());
//		offerAcceptanceService.initiateOffer("a", "b", co);
//		offerAcceptanceService.initiateOffer("a", "c", co);
//
//		List<OfferAcceptance> offerList = offerAcceptanceService.findAll();
//		assertEquals(1, offerList.size());
//		assertEquals("a", offerList.get(0).getInitiator());
//		assertEquals("c", offerList.get(0).getInitiated());
//		assertEquals(co.getId(), offerList.get(0).getOfferId());
//	}
//
//	@Test
//	public void testInitializeOfferFromBothSides() {
//		CargoOffer co = new CargoOffer();
//		co.setAddressFrom("Opole");
//		cargoOfferService.save(co);
//
//		assertEquals(0, offerAcceptanceService.findAll().size());
//		assertEquals(0, acceptedOfferService.findAll().size());
//		assertEquals(1, cargoOfferService.findAll().size());
//
//		offerAcceptanceService.initiateOffer("a", "b", co);
//
//		assertEquals(1, offerAcceptanceService.findAll().size());
//		assertEquals(0, acceptedOfferService.findAll().size());
//		assertEquals(1, cargoOfferService.findAll().size());
//
//		offerAcceptanceService.initiateOffer("b", "a", co);
//
//		assertEquals(0, offerAcceptanceService.findAll().size());
//		assertEquals(1, acceptedOfferService.findAll().size());
//		assertEquals(1, cargoOfferService.findAll().size());
//
//		List<AcceptedOffer> acceptedOffers = acceptedOfferService.findAll();
//		CargoOffer cargoOffer = cargoOfferService.findById(acceptedOffers.get(0).getOfferId());
//
//		assertEquals(cargoOffer.getAddressFrom(), "Opole");
//	}
//
//	@Test
//	public void testAcceptedOfferHasCorrectuser() {
//		CargoOffer co = new CargoOffer();
//		co.setAddressFrom("Opole");
//		co.setOwner("b");
//		cargoOfferService.save(co);
//
//		offerAcceptanceService.initiateOffer("a", "b", co);
//		offerAcceptanceService.initiateOffer("b", "a", co);
//
//		AcceptedOffer acceptedOffer = acceptedOfferService.findAll().get(0);
//		assertEquals("a", acceptedOffer.getUserName());
//
//	}
//
//	@Test
//	public void testAcceptedOfferHasCorrectUserReversed() {
//		CargoOffer co = new CargoOffer();
//		co.setAddressFrom("Opole");
//		co.setOwner("b");
//		cargoOfferService.save(co);
//
//		offerAcceptanceService.initiateOffer("b", "a", co);
//		offerAcceptanceService.initiateOffer("a", "b", co);
//
//		AcceptedOffer acceptedOffer = acceptedOfferService.findAll().get(0);
//		assertEquals("a", acceptedOffer.getUserName());
//
//	}
//
//	
// }
