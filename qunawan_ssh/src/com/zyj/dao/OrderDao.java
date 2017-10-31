package com.zyj.dao;

import java.sql.Connection;
import java.util.List;

import com.zyj.entity.Orders;
import com.zyj.entity.Sequence;


public interface OrderDao {
	/**
	 * ��ҳ��ȡ�û�δ�����Ķ���
	 * @param userId �û�id
	 * @param sq δ����״̬����
	 * @param page ҳ��
	 * @return ��������
	 */
	List<Orders> getOrders(int userId, Sequence sq, int page, int max);
	
	/**
	 * ��õ�ǰ�û��Ķ�����ҳ�б�
	 * @param userId �û�id
	 * @param page ҳ��
	 * @param maxResult ÿҳ�����ֵ
	 * @return ��ҳ��������
	 */
	List<Orders> getOrders(int userId, int page, int max);
	
	/**
	 * ��ȡ����ĳ�û��Ķ�������
	 * @param userId �û�id
	 * @return ������������
	 */
	int getOrdersbyUser(Integer userId);

	/**
	 * ��ȡĳ�û�����״̬�Ķ�������
	 * @param userId �û�id
	 * @param sq ״̬����
	 * @return ������������
	 */
	int getOrdersbyUser(Integer userId, Sequence sq);
	
	/**
	 * ͨ��id���Ҷ���
	 * @return ��������
	 */
	Orders getOrderById(int id);
	
	/**
	 * ���¶���-����
	 * @param state ��Ҫ���µ���״̬
	 * @param id ����id
	 */
	void updateOrderState(Sequence state, Integer id, Connection con);
	
	/**
	 * ���¶���-����
	 * @param state ��Ҫ���µ���״̬
	 * @param id ����id
	 */
	void updateOrderState(Sequence state, Integer id);

	/**
	 * ���涩��
	 * @param con ������������Ӷ���
	 * @param order ��������
	 * @return �ö�����¼��idֵ
	 */
	int savaOrder(Connection con, Orders order);
}
