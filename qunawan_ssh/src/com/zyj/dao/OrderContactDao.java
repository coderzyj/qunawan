package com.zyj.dao;

import java.sql.Connection;
import java.util.List;

import com.zyj.entity.Ordercontact;



public interface OrderContactDao {
	/**
	 * ͨ����ϵ�˵������Լ�������Ż�ȡ��������ϵ��֮��Ĺ�ϵ
	 * 
	 * @param orderId
	 *            �������
	 * @param type
	 *            ��ϵ�˵�����
	 * @return ������ϵ�˹�ϵ����
	 */
	List<Ordercontact> getOrderContacts(int orderId, int type);

	/**
	 * ��ȡ������ϵ�˵����ж�����ϵ�˹�ϵ
	 * 
	 * @param contactId
	 *            ��ϵ��id
	 * @return ���ض�����ϵ�˹�ϵ����
	 */
	int getOrderContactsCount(int contactId);
	
	/**
	 * ��Ӷ�����ϵ��
	 * @param con ���������������Ӷ���
	 * @param oc ������ϵ�˶���
	 */
	void saveOrderContact(Connection con, Ordercontact oc);
}
