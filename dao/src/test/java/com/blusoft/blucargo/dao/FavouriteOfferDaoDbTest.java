package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.FavouriteOffer;

public class FavouriteOfferDaoDbTest extends AbstractDAOTest {

	@Autowired
	FavouriteOfferDao favouriteOfferDao;

	@Autowired
	CargoOfferDao cargoOfferDao;

	private CargoOffer co;
	private FavouriteOffer ao;

	@Before
	public void setUp() {
		co = new CargoOffer();
		co.setOwner("a");
		cargoOfferDao.saveOrUpdate(co);

		ao = new FavouriteOffer();
		ao.setOfferId(co.getId());
		ao.setUserName("b");
		favouriteOfferDao.saveOrUpdate(ao);
	}

	@Test
	public void testGetFavouriteCargoOffersByOwner() {
		List<FavouriteOffer> favouriteOffers = favouriteOfferDao.findAll();
		assertTrue(favouriteOffers.size() > 0);
		assertEquals(favouriteOffers.get(0).getUserName(), "b");
		assertEquals(favouriteOffers.get(0).getOfferId(), co.getId());
	}

	@Test
	public void testRemoveAcceptedCargoOffersByOwner() {

		List<FavouriteOffer> offers = favouriteOfferDao.findAll();

		favouriteOfferDao.removeFavouriteOfferByCargoOfferAndOwner(co, "b");
		List<FavouriteOffer> favouriteOffers = favouriteOfferDao.findAll();
		assertTrue(favouriteOffers.size() == 0);
	}

}
