package com.zyj.dao;

import java.sql.Connection;
import java.util.List;




import com.zyj.entity.Trip;
import com.zyj.form.SearchForm;



public interface TripDao {
	/**
	 * ��ȡɸѡ���õ���ȫ���г̼�¼
	 * 
	 * @param vo
	 *            ɸѡ��
	 * @return �����г̼�¼
	 * 
	 */
	List<Trip> getAllTripByCondition(SearchForm vo);

	/**
	 * ͨ��ɸѡ����ȡ��ҳ�г��б�
	 * 
	 * @param vo
	 *            ɸѡ��
	 * @return ��ҳ�г��б�
	 */
	List<Trip> getPageTripByCondition(SearchForm vo, Integer start, Integer maxCount);
	List<Trip> getAllTripByCondition();

	/**
	 * ͨ��ɸѡ����ȡ��ҳ�г��б�
	 * 
	 * @param vo
	 *            ɸѡ��
	 * @return ��ҳ�г��б�
	 */
	List<Trip> getPageTripByCondition( Integer start, Integer maxCount);
	
	/**
	 * ͨ���г����Ͳ��ҷ�ҳ�г��б�
	 * @param id �г�����id
	 * @param start ��ҳ��ʼ����
	 * @param maxCount ÿҳ���ֵ
	 * @return ��ҳ�г��б�
	 */
	List<Trip> getPageTripsByType(int id, int start, int maxCount);
	
	/**
	 * ͨ��id�����г�
	 * @param id �г�id
	 * @return Trip����
	 */
	Trip getTripById(int id);

	/**
	 *  �����г̷���
	 */
	void updateScore(Trip trip);
}
