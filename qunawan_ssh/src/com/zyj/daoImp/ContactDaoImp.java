package com.zyj.daoImp;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.ContactDao;
import com.zyj.entity.Contact;
import com.zyj.entity.User;
@Repository
public class ContactDaoImp implements ContactDao{
	private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}

	@Override
	public void delete(Integer contact) {
		// TODO Auto-generated method stub
		getSession().delete(getSession().get(Contact.class, contact));
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
		String hql="from Contact c where c.user.id=?";
		return (List<Contact>)getSession().createQuery(hql).setParameter(0, userId).setFirstResult((page-1)*maxResult).setMaxResults(maxResult).list();
	}

	@Override
	public List<Contact> getAllContactByUser(int id) {
		// TODO Auto-generated method stub
		String hql="from Contact c where c.user.id=?";
		return (List<Contact>)getSession().createQuery(hql).setParameter(0, id).list();
	}

	@Override
	public Integer saveContact(Connection con, Contact c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveContact(Contact contact,int userId) {
		// TODO Auto-generated method stub

		contact.setUser((User)getSession().get(User.class, userId));
		getSession().save(contact);
	}

	@Override
	public Integer updateContact(Connection con, Contact c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateContact(Contact c) {
		// TODO Auto-generated method stub
		getSession().update(c);
		return c.getId();
	}

	@Override
	public Contact getContactById(int id) {
		// TODO Auto-generated method stub
		
		return (Contact)getSession().get(Contact.class,id);
	}
	
}
