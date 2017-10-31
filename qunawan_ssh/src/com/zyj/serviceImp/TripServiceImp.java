package com.zyj.serviceImp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
















import com.zyj.dao.CityDao;
import com.zyj.dao.PlaceDao;
import com.zyj.dao.SequenceDao;
import com.zyj.dao.ThemeDao;
import com.zyj.dao.TripDao;
import com.zyj.entity.City;
import com.zyj.entity.Place;
import com.zyj.entity.Sequence;
import com.zyj.entity.Theme;
import com.zyj.entity.Trip;
import com.zyj.entity.Trippicture;
import com.zyj.form.SearchBean;
import com.zyj.form.SearchForm;
import com.zyj.service.TripService;
import com.zyj.utils.Utils;

@Service

public class TripServiceImp implements TripService{
	private TripDao dao;
	private SequenceDao sequenceDao;
	private CityDao cityDao;
	private PlaceDao placeDao;
	private ThemeDao themeDao;
	private List<Trip> allTripList = new ArrayList<>();
	@Resource(name="themeDaoImp")
	public void setThemeDao(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}

	@Resource(name="placeDaoImp")
	public void setPlaceDao(PlaceDao placeDao) {
		this.placeDao = placeDao;
	}

	@Resource(name="cityDaoImp")
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Resource(name="sequenceDaoImp")
	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

	@Resource(name="tripDaoImp")
	public void setDao(TripDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Trip> getAllTripByCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Trip> getPageTripByCondition(Integer start, Integer maxCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Trip> getPageTripsByType(int id, int start, int maxCount) {
		// TODO Auto-generated method stub
		return dao.getPageTripsByType(id, start, maxCount);
	}

	@Transactional
	public Trip getTripById(int id) {
		// TODO Auto-generated method stub
		return dao.getTripById(id);
	}

	@Transactional
	public void updateScore(Trip trip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTripPicture(Set<Trippicture> pictures, String basePath) {
		// TODO Auto-generated method stub
		for (Trippicture tp : pictures) {
			String path = basePath + "image_cache\\" + tp.getName();
			if (!new File(path).exists()) {
				Utils.getFile(tp.getData(), path);
			}
		}
	}

	@Transactional
	public List<Trip> getSearchTripsByVo(SearchForm vo) {
		// TODO Auto-generated method stub
		
		packageForm(vo);
		allTripList = dao.getAllTripByCondition(vo);
		return dao.getPageTripByCondition(vo, vo.getFistResult(), vo.getMaxResult());
	}

	@Transactional
	public SearchBean getSearchBean(SearchForm vo) {
		// TODO Auto-generated method stub
		SearchBean bean = new SearchBean(vo, allTripList);
		return bean;
	}
	private void packageForm(SearchForm vo) {
		// 如果行程类型字符串不为空，获取该行程类型的id
		if (vo.getType_name() != null) {
			String typename = vo.getType_name();
			Sequence s = sequenceDao.getSeqByValue(typename);
			if (s == null) {
				typename = Utils.isoToUtf(typename);
				vo.setType_name(typename);
				s = sequenceDao.getSeqByValue(typename);
			}
			vo.setType_id(s.getId());
		}
		// 如果出发地字符串不为空，获取该出发地的id
		if (vo.getStart_place_name() != null) {
			List<City> citys = cityDao.getCityByName(vo.getStart_place_name());
			List<Integer> cidList = new ArrayList<>();
			if (citys != null) {
				for(City city : citys){
					cidList.add(city.getId());
				}
			}
			vo.setStart_place_id_list(cidList);
		}
		// 如果景点地区字符串不为空，获取该景点地区的id
		if (vo.getPlace_name() != null) {
			List<Place> pList = placeDao.getPlaceByName(vo.getPlace_name());
			List<Integer> pidList = new ArrayList<>();
			if (pList != null && !pList.isEmpty()) {
				for (Place p : pList) {
					pidList.add(p.getId());
				}
			}
			vo.setPlace_id_list(pidList);
		}
		// 如果主题字符串不为空，获取该主题的id
		if (vo.getTheme_name() != null) {
			List<Theme> tList = themeDao.getThemeByName(vo.getTheme_name());
			List<Integer> tidList = new ArrayList<>();
			if (tList != null && !tList.isEmpty()) {
				for (Theme t : tList) {
					tidList.add(t.getId());
				}
			}
			vo.setTheme_id_list(tidList);
		}
	}
}
