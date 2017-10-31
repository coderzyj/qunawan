package com.zyj.dao;

import java.sql.Connection;
import java.util.List;

import com.zyj.entity.Contact;



public interface ContactDao {

	/**
	 * ɾ����ϵ��
	 */
	void delete(Integer contact);
	
	/**
	 * ͨ���û���ȡ��ϵ������
	 * @param userId ��ǰ��½�û�Id
	 * @return ��ϵ����
	 */
	Integer getContactByUser(Integer userId);

	List<Contact> getAllContactPerPage(Integer userId, int page, int maxResult);
	
	/**
	 * ��ȡĳ�û���������ϵ��
	 * @param id �û�id
	 * @return ��ϵ���б�
	 */
	List<Contact> getAllContactByUser(int id);
	
	/**
	 * �µ�����Ҫ���еı������
	 * @param con ���������������Ӷ���
	 * @param c ��ϵ�˶���
	 * @return �������ϵ�˵�idֵ
	 */
	Integer saveContact(Connection con, Contact c);
	
	/**
	 * �����µ���ϵ��
	 * @param contact ��ϵ�˶���
	 * @param userId  ��ϵ�˶�Ӧ���û�����
	 */
	void saveContact(Contact contact,int userId);
	
	/**
	 * ������ϵ��
	 * @param con ���������������Ӷ���
	 * @param c ��ϵ�˶���
	 * @return ���µ���ϵ�˵�idֵ
	 */
	Integer updateContact(Connection con, Contact c);
	
	/**
	 * �����������ϵ��
	 * @param c ��ϵ�˶���
	 * @return ���µ���ϵ��idֵ
	 */
	Integer updateContact(Contact c);
	
	/**
	 * ͨ��id��ȡ��ϵ��
	 * @param id ��ϵ��idֵ
	 * @return ��ϵ�˶���
	 */
	Contact getContactById(int id);
}
