package com.zyj.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zyj.globle.Constants;



public class UserAccessRecorder {

	// ��ҳ�ķ��ʴ���
	private Integer INDEX_ACCESS_NUM = 0;
	// ����ҳ�ķ��ʴ���
	private Integer SEARCH_ACCESS_NUM = 0;
	// ��������ҳ�ķ��ʴ���
	private Integer TRIP_DETAIL_ACCESS_NUM = 0;
	// ���ڴ��ҳ����ʴ����ͷ���ʱ���HashMap����
	private Map<String, String> accessMap = new HashMap<>();
	// ���ڶ�Map���Ͻ���ʱ���������ArrayList����
	private ArrayList<Map.Entry<String, String>> accessList = null;

	/**
	 * ��Map����ŵ�List�У���ͨ��ʱ���ַ�������������
	 * @return ��ʱ���������List
	 */
	public List<Map.Entry<String, String>> getAccessList() {
		// ͨ����ŷ��ʼ�¼��Map��entry����ʵ����ArrayList����
		accessList = new ArrayList<>(accessMap.entrySet());
		/*
		 * �Ը�List���Ͻ������� �������Ƚ�����һ�������ڲ��� ͨ��entry��ʱ���ַ������бȽ�����
		 */
		Collections.sort(accessList, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		return accessList;
	}

	/**
	 * ��¼����ҳ���ʱ��ʹ���
	 * @param page ����ҳ��ĳ���
	 */
	public void setAccessMap(String page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// ��ǰ���ʵ�����ҳ
		if (page.equals(Constants.INDEX_PAGE))
			accessMap.put(page + ":" + ++INDEX_ACCESS_NUM, sdf.format(new Date()));
		// ��ǰ���ʵ�������ҳ
		else if (page.equals(Constants.SEARCH_PAGE))
			accessMap.put(page + ":" + ++SEARCH_ACCESS_NUM, sdf.format(new Date()));
		// ��ǰ���ʵ�����������ҳ
		else
			accessMap.put(page + ":" + ++TRIP_DETAIL_ACCESS_NUM, sdf.format(new Date()));
	}
}
