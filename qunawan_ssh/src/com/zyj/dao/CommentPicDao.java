package com.zyj.dao;

import java.sql.Connection;
import java.util.Set;

import com.zyj.entity.Commentpicture;



public interface CommentPicDao {
	Integer save(Commentpicture cp);

	Set<Commentpicture> getCommentPics(Integer commentId);
}
