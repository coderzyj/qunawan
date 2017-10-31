package com.zyj.daoImp;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.CommentDao;
import com.zyj.entity.Comment;
@Repository
public class CommentDaoImp implements CommentDao{
	private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}
	@Override
	public List<Comment> getCommentsPerPage(Integer userId, Integer sq_id,
			Integer page, Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCommentCount(Integer tripId) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Comment c where c.trip.id=?";
		Long l=(Long) getSession().createQuery(hql).setParameter(0, tripId).uniqueResult();
		return l.intValue();
	}

	@Override
	public Integer updateUseful(Integer commentId, Boolean isUseful) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getAvg_Score(String type, Integer tripId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Float[]> getCountByAvg(Integer tripId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getCommentsByTripId(Integer tripId, Integer page,
			Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCommentedCountByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCommentedCountByTrip(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Comment com) {
		// TODO Auto-generated method stub
		return null;
	}

}
