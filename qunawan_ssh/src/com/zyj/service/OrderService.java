package com.zyj.service;

import java.sql.Connection;
import java.util.List;




import com.zyj.entity.Contact;
import com.zyj.entity.Orders;
import com.zyj.entity.Sequence;



public interface OrderService {
	List<Contact> getContactsByOrderId(int orderId);


	Contact getContactByOrderId(int orderId);

	List<Orders> getOrders(int userId, Sequence sq, int page, int max);

	List<Orders> getOrders(int userId, int page, int max);
	
	int getOrdersbyUser(Integer userId);

	int getOrdersbyUser(Integer userId, Sequence sq);

	Orders getOrderById(int id);

	void updateOrderState(Sequence state, Integer id, Connection con);

	void updateOrderState(Sequence state, Integer id);

	int savaOrder(Connection con, Orders order);
}
