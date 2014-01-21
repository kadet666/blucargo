package com.blusoft.blucargo.dao;

import java.util.List;

import com.blusoft.blucargo.model.Comment;
import com.blusoft.blucargo.model.CommentAndOffer;

public interface CommentDao extends BaseDao<Comment> {

	public abstract List<Comment> findCommentsByOfferId(Long id);

	public abstract List<Comment> findCommentsByAuthor(String author);

	public abstract List<CommentAndOffer> findAllCommentsForUser(String user);

	public abstract List<Comment> findAllCommentsForUserSimple(String user);

}