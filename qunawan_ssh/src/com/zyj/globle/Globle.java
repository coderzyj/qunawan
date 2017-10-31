package com.zyj.globle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.zyj.utils.UserAccessRecorder;



/**
 * 全球变量，用于处理评论批量上传图片时存储图片�?
 */
public class Globle {

	private static HashMap<Integer, List<byte[]>> comPicCache = new HashMap<Integer, List<byte[]>>();

	// 用于存放用户访问记录器的Map集合
	private static Map<Integer, UserAccessRecorder> userAccessMap = new HashMap<>();

	/**
	 * 上传�?���?
	 * @param uuid 登陆用户ID
	 * @param pic �?��传图片的二进制数�?
	 */
	public static void uploadPics(Integer uuid, byte[] pic) {
		List<byte[]> pics = comPicCache.get(uuid);
		if (pics == null) {
			pics = new ArrayList<byte[]>();
		}
		pics.add(pic);
		comPicCache.put(uuid, pics);
	}

	/**
	 * 获取某用户全部图片数�?
	 */
	public static List<byte[]> getPics(Integer uuid) {
		if (comPicCache == null)
			return null;
		return comPicCache.get(uuid);
	}

	/**
	 * 清除某用户全部图片数�?
	 */
	public static void clear(Integer uuid) {
		if (comPicCache == null)
			return;
		comPicCache.remove(uuid);
	}

	public static HashMap<Integer, List<byte[]>> getComPicCache() {
		return comPicCache;
	}

	public static void setComPicCache(HashMap<Integer, List<byte[]>> comPicCache) {
		Globle.comPicCache = comPicCache;
	}

	/**
	 * 添加用户访问记录
	 * @param uid 用户id
	 * @param page 访问页面
	 */
	public static void addAccessRecord(Integer uid, String page) {
		UserAccessRecorder recorder = Globle.userAccessMap.get(uid);
		if (recorder == null) {
			Globle.userAccessMap.put(uid, new UserAccessRecorder());
			recorder = Globle.userAccessMap.get(uid);
		}
		recorder.setAccessMap(page);
	}

	/**
	 * 清除用户访问记录�?
	 * @param uid 用户id
	 */
	public static void clearAccessRecorder(Integer uid) {
		Globle.userAccessMap.remove(uid);
	}

	/**
	 * 获取用户访问列表（按时间正序排列�?
	 * @param uid 用户id
	 * @return 访问记录列表
	 */
	public static List<Map.Entry<String, String>> getAccessList(Integer uid) {
		UserAccessRecorder recorder = Globle.userAccessMap.get(uid);
		if (recorder == null) {
			Globle.userAccessMap.put(uid, new UserAccessRecorder());
			recorder = Globle.userAccessMap.get(uid);
		}
		return recorder.getAccessList();
	}
	

	/**
	 * 设置map集合，存放用户已登录的session信息 Map泛型String代表不同用户的标识符userId
	 * Map的泛型HttpSession代表当前用户的会�?
	 */
	public static Map<String, HttpSession> loginSessionMap = new HashMap<String, HttpSession>();
}
