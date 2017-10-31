package com.zyj.daoImp;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.SequenceDao;
import com.zyj.entity.Sequence;
@Repository
public class SequenceDaoImp implements SequenceDao{
	private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}

	@Override
	public Sequence getSeqByValue(String value) {
		// TODO Auto-generated method stub
		String hql = "FROM Sequence WHERE value = ?";
		return (Sequence)getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}

	@Override
	public Sequence getSeqByKeyAndType(String key, String type) {
		// TODO Auto-generated method stub
		String hql = "FROM Sequence WHERE keying = ? AND type = ?";
		return (Sequence)getSession().createQuery(hql).setParameter(0, key).setParameter(1, type).uniqueResult();
	}

	@Override
	public Sequence getSequenceById(int id) {
		// TODO Auto-generated method stub
		String hql = "FROM Sequence WHERE id = ?";
		return (Sequence)getSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}

}
