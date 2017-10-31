package com.zyj.serviceImp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;





import com.zyj.dao.OrderContactDao;
import com.zyj.dao.OrderDao;
import com.zyj.entity.Contact;
import com.zyj.entity.Ordercontact;
import com.zyj.entity.Orders;
import com.zyj.entity.Sequence;
import com.zyj.globle.Constants;
import com.zyj.service.OrderService;
@Service
@Transactional
public class OrderServiceImp implements OrderService{
	private OrderDao dao;
	private OrderContactDao ocdao;
	@Resource(name="orderContactDaoImp")
	public void setOcdao(OrderContactDao ocdao) {
		this.ocdao = ocdao;
	}

	@Resource(name="orderDaoImp")
	public void setDao(OrderDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Orders> getOrders(int userId, Sequence sq, int page, int max) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Orders> getOrders(int userId, int page, int max) {
		// TODO Auto-generated method stub
		return dao.getOrders(userId, page, max);
	}

	@Override
	public int getOrdersbyUser(Integer userId) {
		// TODO Auto-generated method stub
		return dao.getOrdersbyUser(userId);
	}

	@Override
	public int getOrdersbyUser(Integer userId, Sequence sq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Orders getOrderById(int id) {
		// TODO Auto-generated method stub
		
		return dao.getOrderById(id);
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

	@Override
	public List<Contact> getContactsByOrderId(int orderId) {
		// TODO Auto-generated method stub
		List<Contact> ocs = new ArrayList<Contact>();

		List<Ordercontact> orderContacts = ocdao.getOrderContacts(orderId, Constants.CONTACT_FOR_PLAY);
		if (orderContacts == null || orderContacts.size() < 1)
			return null;

		for (Ordercontact oc : orderContacts) {
			ocs.add(oc.getContact());
		}

		return ocs;

	}

	@Override
	public Contact getContactByOrderId(int orderId) {
		// TODO Auto-generated method stub
		List<Ordercontact> orderContacts = ocdao.getOrderContacts(orderId, Constants.CONTACT_FOR_PLAY);
		return orderContacts.get(0).getContact();
	}

}
