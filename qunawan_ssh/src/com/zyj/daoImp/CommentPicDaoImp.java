package com.zyj.daoImp;

import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.CommentPicDao;
import com.zyj.entity.Commentpicture;
@Repository
@Transactional
public class CommentPicDaoImp implements CommentPicDao{
	private SessionFactory sessionFacotry;
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}
	@Override
	public Integer save(Commentpicture cp) {
		// TODO Auto-generated method stub
		
		return (Integer) getSession().save(cp);
	}

	@Override
	public Set<Commentpicture> getCommentPics(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
