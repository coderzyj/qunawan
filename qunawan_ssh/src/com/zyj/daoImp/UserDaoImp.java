package com.zyj.daoImp;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.UserDao;
import com.zyj.entity.User;
import com.zyj.utils.Utils;

@Repository
public class UserDaoImp implements UserDao{
	private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}


	@Override
	public User Login(User user) {
		// TODO Auto-generated method stub
		
		String hql = "from User u where u.phone = ? and u.password = ?";
		User u = (User)getSession().createQuery(hql).setParameter(0, user.getPhone()).
				setParameter(1, Utils.toMD5(user.getPassword())).uniqueResult();
		return u;
	}

	@Override
	public boolean Register(User user) {
		// TODO Auto-generated method stub
		user.setPassword("e10adc3949ba59abbe56e057f20f883e");
		String hql="from User u where u.phone=?";
		User u=(User)getSession().createQuery(hql).setParameter(0, user.getPhone()).
				uniqueResult();
		if(u!=null)
			return false;
		else
		{
			getSession().save(user);
			return true;
		}
	}

	@Override
	public void Update(User user) {
		// TODO Auto-generated method stub
		getSession().update(user);
	}

	@Override
	public User getUserByCondition(String condition) {
		// TODO Auto-generated method stub
		String hql1="from User u where u.phone=? or u.email=?";
		User u=(User) getSession().createQuery(hql1).setParameter(0, condition).setParameter(1, condition).uniqueResult();

		return u;
	}

}
