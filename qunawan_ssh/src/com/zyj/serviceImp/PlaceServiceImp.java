package com.zyj.serviceImp;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zyj.dao.PlaceDao;
import com.zyj.entity.Place;
import com.zyj.service.PlaceService;
@Service
@Transactional
public class PlaceServiceImp implements PlaceService{
	private PlaceDao dao;
	@Resource(name="placeDaoImp")
	public void setDao(PlaceDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Place> getPlaceByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPagePlacesByType(int id, int start, int maxCount) {
		// TODO Auto-generated method stub
		return dao.getPagePlacesByType(id, start, maxCount);
	}

	@Override
	public Place getPlaceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
