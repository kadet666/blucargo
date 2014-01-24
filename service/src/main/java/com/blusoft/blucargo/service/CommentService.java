package com.blusoft.blucargo.services;

import java.util.List;

import com.blusoft.blucargo.model.Comment;
import com.blusoft.blucargo.model.CommentAndOffer;

public interface CommentService {

	public void save(Comment comment);

	public void remove(Comment comment);

	public List<Comment> findAll();

	public Comment findById(long id);

	public List<Comment> findCommentsByOfferId(Long id);

	public List<Comment> findCommentsByAuthor(String author);

	public List<CommentAndOffer> findAllCommentsForUser(String user);

	public List<Comment> findAllCommentsForUserSimple(String user);

}
