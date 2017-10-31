package com.zyj.daoImp;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.CityDao;
import com.zyj.entity.City;
@Repository
@Transactional
public class CityDaoImp implements CityDao{

private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}
	@Override
	public List<City> getAllProvinces() {
		// TODO Auto-generated method stub
		String hql="from City where type=?";
		return (List<City>)getSession().createQuery(hql).setParameter(0, 1).list();
	}

	@Override
	public City getCityById(int id) {
		// TODO Auto-generated method stub
		return (City) getSession().get(City.class, id);
	}

	@Override
	public List<City> getCityByName(String name) {
		// TODO Auto-generated method stub
		String hql="from City c where c.name=?";
		return (List<City>)getSession().createQuery(hql).setParameter(0, name).list();
	}

}
