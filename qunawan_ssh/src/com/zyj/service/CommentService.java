package com.zyj.service;

import java.sql.Connection;
import java.util.List;
import java.util.Set;







import com.zyj.entity.Comment;
import com.zyj.entity.Commentpicture;
import com.zyj.entity.Sequence;

public interface CommentService {
	public void initCommentPicture(Set<Commentpicture> pictures, String basePath);
List<Comment> getCommentsPerPage(Integer userId, Integer sq_id, Integer page, Integer max);

	Integer getCommentCount(Integer tripId);

	Integer updateUseful(Integer commentId, Boolean isUseful);

	Float getAvg_Score(String type, Integer tripId, Connection con) ;

	List<Float[]> getCountByAvg(Integer tripId, Connection con) ;

	List<Comment> getCommentsByTripId(Integer tripId, Integer page, Integer max);

	Integer getCommentedCountByUser(Integer userId);

	Integer getCommentedCountByTrip(Integer tripId);

	Integer save(Comment com, Connection con);
	public void saveComment(Comment comment, List<byte[]> byteArray, Sequence sq_commented);

}
