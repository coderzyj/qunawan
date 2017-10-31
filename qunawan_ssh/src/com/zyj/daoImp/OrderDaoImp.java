package com.zyj.daoImp;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.OrderDao;
import com.zyj.entity.Orders;
import com.zyj.entity.Sequence;
@Repository
public class OrderDaoImp implements OrderDao{
	private SessionFactory sessionFacotry;
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}
	@Override
	public List<Orders> getOrders(int userId, Sequence sq, int page, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getOrders(int userId, int page, int max) {
		// TODO Auto-generated method stub
		String hql="from Orders t where t.user.id=?";
		
		return (List<Orders>)getSession().createQuery(hql).setParameter(0, userId).setFirstResult((page-1)*max).setMaxResults(max).list();
	}

	@Override
	public int getOrdersbyUser(Integer userId) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Orders o where o.user.id=?";
		Long l=(Long) getSession().createQuery(hql).setParameter(0, userId).uniqueResult();
		return l.intValue();
	}

	@Override
	public int getOrdersbyUser(Integer userId, Sequence sq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Orders getOrderById(int id) {
		// TODO Auto-generated method stub
		String hql="from Orders o where o.id=?";
		
		return (Orders)getSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}

	@Override
	public void updateOrderState(Sequence state, Integer id, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrderState(Sequence state, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int savaOrder(Connection con, Orders order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
