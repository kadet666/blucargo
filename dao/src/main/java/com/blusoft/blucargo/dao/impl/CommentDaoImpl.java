package com.blusoft.blucargo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.blusoft.blucargo.dao.CommentDao;
import com.blusoft.blucargo.model.Comment;
import com.blusoft.blucargo.model.CommentAndOffer;

@Repository("comment")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

	protected CommentDaoImpl() {
		super(Comment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findCommentsByOfferId(Long id) {
		String queryString = "SELECT c from Comment c where c.cargoOfferId=:offerId";
		Query query = getSession().createQuery(queryString);
		query.setParameter("offerId", id);
		return (List<Comment>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findCommentsByAuthor(String author) {
		String queryString = "SELECT c from Comment c where c.author=:author";
		Query query = getSession().createQuery(queryString);
		query.setParameter("author", author);
		return (List<Comment>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CommentAndOffer> findAllCommentsForUser(String user) {
		String queryString = "SELECT c FROM CommentAndOffer c where c.owner=:user";
		Query query = getSession().createQuery(queryString);
		query.setParameter("user", user);
		List<CommentAndOffer> o = (List<CommentAndOffer>) query.list();
		return o;
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findAllCommentsForUserSimple(String owner) {
		String queryString = "SELECT DISTINCT t3 from Comment t3 where t3.cargoOfferId in (select offer.id from CargoOffer offer where offer.owner=:owner)";
		Query query = getSession().createQuery(queryString);
		query.setParameter("owner", owner);
		List<Comment> o = (List<Comment>) query.list();

		List<Comment> ordinaryList = new ArrayList<Comment>(o);

		return ordinaryList;
	}

}
