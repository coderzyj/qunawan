package com.zyj.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zyj.dao.PlaceDao;
import com.zyj.entity.Place;
@Repository
public class PlaceDaoImp implements PlaceDao{
private SessionFactory sessionFacotry;
	
	@Resource(name="sessionFactory")
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	public Session getSession(){
		return sessionFacotry.getCurrentSession();
	}
	@Override
	public List<Place> getPlaceByName(String name) {
		// TODO Auto-generated method stub
		String hql="from Place p where p.name=?";
		
		return (List<Place>)getSession().createQuery(hql).setParameter(0, name).list();
	}

	@Override
	public List<String> getPagePlacesByType(int id, int start, int maxCount) {
		// TODO Auto-generated method stub
		String hql="select distinct t.place.name from Placeontrip t where t.trip.sequence.id=?";
		return (List<String>)getSession().createQuery(hql).setParameter(0, id).setFirstResult(start).setMaxResults(maxCount).list();
	}

	@Override
	public Place getPlaceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
