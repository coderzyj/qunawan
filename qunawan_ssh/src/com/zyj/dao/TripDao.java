package com.zyj.dao;

import java.sql.Connection;
import java.util.List;




import com.zyj.entity.Trip;
import com.zyj.form.SearchForm;



public interface TripDao {
	/**
	 * 获取筛选表单得到的全部行程记录
	 * 
	 * @param vo
	 *            筛选表单
	 * @return 所有行程记录
	 * 
	 */
	List<Trip> getAllTripByCondition(SearchForm vo);

	/**
	 * 通过筛选表单获取分页行程列表
	 * 
	 * @param vo
	 *            筛选表单
	 * @return 分页行程列表
	 */
	List<Trip> getPageTripByCondition(SearchForm vo, Integer start, Integer maxCount);
	List<Trip> getAllTripByCondition();

	/**
	 * 通过筛选表单获取分页行程列表
	 * 
	 * @param vo
	 *            筛选表单
	 * @return 分页行程列表
	 */
	List<Trip> getPageTripByCondition( Integer start, Integer maxCount);
	
	/**
	 * 通过行程类型查找分页行程列表
	 * @param id 行程类型id
	 * @param start 分页开始索引
	 * @param maxCount 每页最大值
	 * @return 分页行程列表
	 */
	List<Trip> getPageTripsByType(int id, int start, int maxCount);
	
	/**
	 * 通过id查找行程
	 * @param id 行程id
	 * @return Trip对象
	 */
	Trip getTripById(int id);

	/**
	 *  更新行程分数
	 */
	void updateScore(Trip trip);
}
