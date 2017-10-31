package com.zyj.dao;

import java.sql.Connection;
import java.util.List;

import com.zyj.entity.Ordercontact;



public interface OrderContactDao {
	/**
	 * 通过联系人的类型以及订单编号获取订单与联系人之间的关系
	 * 
	 * @param orderId
	 *            订单编号
	 * @param type
	 *            联系人的类型
	 * @return 订单联系人关系集合
	 */
	List<Ordercontact> getOrderContacts(int orderId, int type);

	/**
	 * 获取单个联系人的所有订单联系人关系
	 * 
	 * @param contactId
	 *            联系人id
	 * @return 返回订单联系人关系集合
	 */
	int getOrderContactsCount(int contactId);
	
	/**
	 * 添加订单联系人
	 * @param con 用于事务管理的连接对象
	 * @param oc 订单联系人对象
	 */
	void saveOrderContact(Connection con, Ordercontact oc);
}
