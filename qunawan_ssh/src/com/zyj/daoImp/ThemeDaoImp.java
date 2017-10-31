package com.zyj.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.ThemeDao;
import com.zyj.entity.Theme;
@Repository
public class ThemeDaoImp implements ThemeDao{
private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}
	@Override
	public List<Theme> getThemeByName(String name) {
		// TODO Auto-generated method stub
		String hql="from Theme t where t.name=?";
		
		return (List<Theme>)getSession().createQuery(hql).setParameter(0, name).list();
	}

	@Override
	public List<String> getPageThemesByType(int id, int start, int maxCount) {
		// TODO Auto-generated method stub
		String hql="select distinct t.theme.name from Themeontrip t where t.trip.sequence.id=?";
		return (List<String>)getSession().createQuery(hql).setParameter(0, id).setFirstResult(start).setMaxResults(maxCount).list();
	}

	@Override
	public Theme getThemeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
