package com.zyj.service;

import java.util.List;
import java.util.Set;












import com.zyj.entity.Trip;
import com.zyj.entity.Trippicture;
import com.zyj.form.SearchBean;
import com.zyj.form.SearchForm;

public interface TripService {
	List<Trip> getAllTripByCondition();
	List<Trip> getPageTripByCondition( Integer start, Integer maxCount);
	List<Trip> getPageTripsByType(int id, int start, int maxCount);
	Trip getTripById(int id);
	void updateScore(Trip trip);
	public void initTripPicture(Set<Trippicture> pictures, String basePath); 
	List<Trip> getSearchTripsByVo(SearchForm vo);
	SearchBean getSearchBean(SearchForm vo);
}
