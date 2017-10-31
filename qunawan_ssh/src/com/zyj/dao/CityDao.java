package com.zyj.dao;

import java.util.List;

import com.zyj.entity.City;



public interface CityDao {
	public List<City> getAllProvinces();
	City getCityById(int id);
	List<City> getCityByName(String name);
}
