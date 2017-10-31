package com.zyj.action;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zyj.entity.Trip;
import com.zyj.entity.User;
import com.zyj.globle.Constants;
import com.zyj.service.PlaceService;
import com.zyj.service.SequenceService;
import com.zyj.service.ThemeService;
import com.zyj.service.TripService;
@Controller("indexaction")
@Scope("prototype")
public class IndexAction extends ActionSupport{
	private String[] tripTypes = Constants.TRIP_TYPE;
	private SequenceService sequenceDao;
	private TripService tripDao;
	private PlaceService placeDao;
	private ThemeService themeDao;
	// 显示的行程列表
	Map<String, List<Trip>> tripMap = new HashMap<>();
			// 页面显示的景点列表
	Map<String, List<String>> placeMap = new HashMap<>();
			// 页面显示的主题列表
	Map<String, List<String>> themeMap = new HashMap<>();
	// 使用'自驾游'、'国内游'和'出境游'为键，存放各自要显示的列表集合
	
	
	@Resource(name="sequenceServiceImp")
	public void setSequenceDao(SequenceService sequenceDao) {
		this.sequenceDao = sequenceDao;
	}
	@Resource(name="themeServiceImp")
	public void setThemeDao(ThemeService themeDao) {
		this.themeDao = themeDao;
	}

	@Resource(name="placeServiceImp")
	public void setPlaceDao(PlaceService placeDao) {
		this.placeDao = placeDao;
	}


	public TripService getTripDao() {
		return tripDao;
	}

	@Resource(name="tripServiceImp")
	public void setTripDao(TripService tripDao) {
		this.tripDao = tripDao;
	}


	public String showAll()
	{
		for (int i = 0; i < 3; i++) {
			int typeId = sequenceDao.getSeqByValue(tripTypes[i]).getId();
			List<Trip> items = tripDao.getPageTripsByType(typeId, 0, 6);
			tripMap.put(tripTypes[i], items);
			for (Trip trip : items){
				//System.out.println(trip.getTitle());
				tripDao.initTripPicture(trip.getTrippictures(),
						ServletActionContext.getServletContext().getRealPath("/"));
			}

			placeMap.put(tripTypes[i], placeDao.getPagePlacesByType(typeId, 0, 10));
			themeMap.put(tripTypes[i], themeDao.getPageThemesByType(typeId, 0, 10));
		}
		HttpServletRequest request= (HttpServletRequest)ServletActionContext.getRequest();
		request.setAttribute(Constants.INDEX_TRIP_MAP, tripMap);
		request.setAttribute(Constants.INDEX_PLACE_MAP, placeMap);
		request.setAttribute(Constants.INDEX_THEME_MAP, themeMap);
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//System.out.println(user.getName()+","+user.getPhone()+"11111111111111"+user.getPassword());
		return "success";
	}


	public SequenceService getSequenceDao() {
		return sequenceDao;
	}


	public String[] getTripTypes() {
		return tripTypes;
	}


	public void setTripTypes(String[] tripTypes) {
		this.tripTypes = tripTypes;
	}


	public Map<String, List<Trip>> getTripMap() {
		return tripMap;
	}


	public void setTripMap(Map<String, List<Trip>> tripMap) {
		this.tripMap = tripMap;
	}


	public Map<String, List<String>> getPlaceMap() {
		return placeMap;
	}


	public void setPlaceMap(Map<String, List<String>> placeMap) {
		this.placeMap = placeMap;
	}


	public Map<String, List<String>> getThemeMap() {
		return themeMap;
	}


	public void setThemeMap(Map<String, List<String>> themeMap) {
		this.themeMap = themeMap;
	}


	
}
