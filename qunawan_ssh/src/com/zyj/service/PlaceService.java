package com.zyj.service;

import java.util.List;

import com.zyj.entity.Place;

public interface PlaceService {
List<Place> getPlaceByName(String name);
	
	List<String> getPagePlacesByType(int id ,int start, int maxCount);

	Place getPlaceById(int id);
}
