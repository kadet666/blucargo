package com.blusoft.blucargo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.Comment;
import com.blusoft.blucargo.model.EndedOffer;

public class CommentDaoDbTest extends AbstractDAOTest {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CargoOfferDao offerDao;

	@Autowired
	private EndedOfferDao endedOfferDao;

	@Before
	public void setUp() {
	}

	@Test
	public void testSaveComment() {

		Comment c = new Comment();
		c.setAuthor("a");
		commentDao.saveOrUpdate(c);

		List<Comment> comments = commentDao.findAll();

		assertEquals(1, comments.size());
		assertEquals(c.getId(), comments.get(0).getId());

	}

	@Test
	public void testCommentHasDate() {

		Comment c = new Comment();
		c.setAuthor("a");
		commentDao.saveOrUpdate(c);

		List<Comment> comments = commentDao.findAll();

		assertEquals(1, comments.size());
		assertNotNull(comments.get(0).getDate());

	}

	@Test
	public void testFindCommentsByOfferId() {
		CargoOffer o = new CargoOffer();
		offerDao.saveOrUpdate(o);

		Comment c1 = new Comment();
		c1.setCargoOfferId(o.getId());
		c1.setAuthor("a");

		Comment c2 = new Comment();
		c2.setCargoOfferId(o.getId());
		c2.setAuthor("a");

		Comment c3 = new Comment();
		c3.setCargoOfferId(o.getId());
		c3.setAuthor("a");

		Comment c4 = new Comment();
		c4.setCargoOfferId(o.getId());
		c4.setAuthor("a");

		commentDao.saveOrUpdate(c1);
		commentDao.saveOrUpdate(c2);
		commentDao.saveOrUpdate(c3);
		commentDao.saveOrUpdate(c4);

		List<Comment> comments = commentDao.findCommentsByOfferId(o.getId());
		assertEquals(4, comments.size());

		List<Comment> comments2 = commentDao.findCommentsByOfferId(3L);
		assertEquals(0, comments2.size());

	}

	@Test
	public void testFindCommentsByAuthor() {
		CargoOffer o1 = new CargoOffer();
		offerDao.saveOrUpdate(o1);
		CargoOffer o2 = new CargoOffer();
		offerDao.saveOrUpdate(o1);
		CargoOffer o3 = new CargoOffer();
		offerDao.saveOrUpdate(o1);
		CargoOffer o4 = new CargoOffer();
		offerDao.saveOrUpdate(o1);

		Comment c1 = new Comment();
		c1.setAuthor("a");
		c1.setCargoOfferId(o1.getId());
		Comment c2 = new Comment();
		c2.setAuthor("a");
		c2.setCargoOfferId(o2.getId());
		Comment c3 = new Comment();
		c3.setAuthor("a");
		c3.setCargoOfferId(o3.getId());
		Comment c4 = new Comment();
		c4.setAuthor("a");
		c4.setCargoOfferId(o4.getId());

		// Dummy comments
		Comment c5 = new Comment();
		c5.setAuthor("b");
		c5.setCargoOfferId(o4.getId());
		Comment c6 = new Comment();
		c6.setAuthor("b");
		c6.setCargoOfferId(o4.getId());

		commentDao.saveOrUpdate(c1);
		commentDao.saveOrUpdate(c2);
		commentDao.saveOrUpdate(c3);
		commentDao.saveOrUpdate(c4);

		List<Comment> comments = commentDao.findCommentsByAuthor("a");
		assertEquals(4, comments.size());
		assertEquals(o1.getId(), comments.get(0).getCargoOfferId());
		assertEquals(o2.getId(), comments.get(1).getCargoOfferId());
		assertEquals(o3.getId(), comments.get(2).getCargoOfferId());
		assertEquals(o4.getId(), comments.get(3).getCargoOfferId());

	}

	@Test
	public void testFindCommentsByUsername() {
		CargoOffer o1 = new CargoOffer();
		o1.setOwner("b");
		CargoOffer o2 = new CargoOffer();
		o2.setOwner("b");
		CargoOffer o3 = new CargoOffer();
		o3.setOwner("b");
		CargoOffer o4 = new CargoOffer();
		o4.setOwner("b");
		offerDao.saveOrUpdate(o1);
		offerDao.saveOrUpdate(o2);
		offerDao.saveOrUpdate(o3);
		offerDao.saveOrUpdate(o4);
		offerDao.findAll();

		// Ended Offers.
		EndedOffer eo1 = new EndedOffer();
		eo1.setOfferId(o1.getId());
		eo1.setUserName("a");
		EndedOffer eo2 = new EndedOffer();
		eo2.setOfferId(o2.getId());
		eo2.setUserName("a");
		EndedOffer eo3 = new EndedOffer();
		eo3.setOfferId(o3.getId());
		eo3.setUserName("a");
		EndedOffer eo4 = new EndedOffer();
		eo4.setOfferId(o4.getId());
		eo4.setUserName("a");

		endedOfferDao.saveOrUpdate(eo1);
		endedOfferDao.saveOrUpdate(eo2);
		endedOfferDao.saveOrUpdate(eo3);
		endedOfferDao.saveOrUpdate(eo4);
		// endedOfferService.findAll();

		Comment c1 = new Comment();
		c1.setAuthor("a");
		c1.setCargoOfferId(o1.getId());
		Comment c2 = new Comment();
		c2.setAuthor("a");
		c2.setCargoOfferId(o2.getId());
		Comment c3 = new Comment();
		c3.setAuthor("a");
		c3.setCargoOfferId(o3.getId());
		Comment c4 = new Comment();
		c4.setAuthor("a");
		c4.setCargoOfferId(o4.getId());

		// Dummy comments
		Comment c5 = new Comment();
		c5.setAuthor("b");
		c5.setCargoOfferId(o4.getId());
		Comment c6 = new Comment();
		c6.setAuthor("b");
		c6.setCargoOfferId(o4.getId());

		commentDao.saveOrUpdate(c1);
		commentDao.saveOrUpdate(c2);
		commentDao.saveOrUpdate(c3);
		commentDao.saveOrUpdate(c4);

		List<Comment> comments = commentDao.findAllCommentsForUserSimple("b");
		assertEquals(4, comments.size());
		assertEquals(o1.getId(), comments.get(0).getCargoOfferId());
		assertEquals(o2.getId(), comments.get(1).getCargoOfferId());
		assertEquals(o3.getId(), comments.get(2).getCargoOfferId());
		assertEquals(o4.getId(), comments.get(3).getCargoOfferId());

	}

}
