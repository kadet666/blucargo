package com.blusoft.blucargo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blusoft.blucargo.dao.CommentDao;
import com.blusoft.blucargo.model.Comment;
import com.blusoft.blucargo.model.CommentAndOffer;
import com.blusoft.blucargo.services.CommentService;

@Transactional
@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	public synchronized void save(Comment comment) {
		this.commentDao.saveOrUpdate(comment);
	}

	public synchronized void remove(Comment comment) {
		this.commentDao.delete(comment);
	}

	public synchronized List<Comment> findAll() {
		return commentDao.findAll();
	}

	public synchronized Comment findById(long id) {
		return commentDao.findById(id);
	}

	public List<Comment> findCommentsByOfferId(Long id) {
		return commentDao.findCommentsByOfferId(id);
	}

	public List<Comment> findCommentsByAuthor(String author) {
		return commentDao.findCommentsByAuthor(author);
	}

	public List<CommentAndOffer> findAllCommentsForUser(String user) {
		return commentDao.findAllCommentsForUser(user);
	}

	public List<Comment> findAllCommentsForUserSimple(String user) {
		return commentDao.findAllCommentsForUserSimple(user);
	}

}
