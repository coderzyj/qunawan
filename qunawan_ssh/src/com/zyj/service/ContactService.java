package com.zyj.service;

import java.sql.Connection;
import java.util.List;

import com.zyj.entity.Contact;

public interface ContactService {
	/**
	 * É¾³ýÁªÏµÈË
	 */
	void delete(Integer contact);

	Integer getContactByUser(Integer userId);

	List<Contact> getAllContactPerPage(Integer userId, int page, int maxResult);

	List<Contact> getAllContactByUser(int id);

	Integer saveContact(Connection con, Contact c);

	void saveContact(Contact contact,int userId);

	Integer updateContact(Connection con, Contact c);

	Integer updateContact(Contact c);

	Contact getContactById(int id);
}
