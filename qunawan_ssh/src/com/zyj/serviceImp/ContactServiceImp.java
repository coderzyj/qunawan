package com.zyj.serviceImp;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zyj.dao.ContactDao;
import com.zyj.entity.Contact;
import com.zyj.service.ContactService;
@Service
@Transactional
public class ContactServiceImp implements ContactService{
	private ContactDao dao;
	@Resource(name="contactDaoImp")
	public void setDao(ContactDao dao) {
		this.dao = dao;
	}

	@Override
	public void delete(Integer contact) {
		// TODO Auto-generated method stub
		dao.delete(contact);
	}

	@Override
	public Integer getContactByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getAllContactPerPage(Integer userId, int page,
			int maxResult) {
		// TODO Auto-generated method stub
		return dao.getAllContactPerPage(userId, page, maxResult);
	}

	@Override
	public List<Contact> getAllContactByUser(int id) {
		// TODO Auto-generated method stub
		return dao.getAllContactByUser(id);
	}

	@Override
	public Integer saveContact(Connection con, Contact c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveContact(Contact contact,int userId) {
		// TODO Auto-generated method stub
		dao.saveContact(contact,userId);
	}

	@Override
	public Integer updateContact(Connection con, Contact c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateContact(Contact c) {
		// TODO Auto-generated method stub

		return dao.updateContact(c);
	}

	@Override
	public Contact getContactById(int id) {
		// TODO Auto-generated method stub
		return dao.getContactById(id);
	}

}
