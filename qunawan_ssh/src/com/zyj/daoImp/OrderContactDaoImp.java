package com.zyj.daoImp;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.OrderContactDao;
import com.zyj.entity.Ordercontact;
@Repository
@Transactional
public class OrderContactDaoImp implements OrderContactDao{
	private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}

	@Override
	public List<Ordercontact> getOrderContacts(int orderId, int type) {
		// TODO Auto-generated method stub
		String hql="from Ordercontact oc where oc.orders.id=? and oc.type=?";
		
		return (List<Ordercontact>)getSession().createQuery(hql).setParameter(0, orderId).setParameter(1, type).list();
	}

	@Override
	public int getOrderContactsCount(int contactId) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Ordercontact o where o.contact.id=?";
		Long l=(Long) getSession().createQuery(hql).setParameter(0, contactId).uniqueResult();
		return l.intValue();
	}

	@Override
	public void saveOrderContact(Connection con, Ordercontact oc) {
		// TODO Auto-generated method stub
		
	}

}
