package com.zyj.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

/**
 * ��ͼ����, ajax����, ������
 */
public final class ControllerUtil {

	private ControllerUtil() {

	}

	/**
	 * ����ַ���
	 * @param response
	 * @param str
	 */
	public static void out(HttpServletResponse response, String str) {
		OutputStream os = null;
		try {
			response.setContentType("text/plain");
			os = response.getOutputStream();
			os.write(str.getBytes("UTF-8"));
			os.flush();
		} catch (IOException e) {
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * �������json��
	 * @param response
	 * @param collection
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void out(HttpServletResponse response, Collection collection) {
		out(response, JSONUtil.listToJson(collection));
	}

	/**
	 * ��������ַ���
	 * @param response
	 * @param object
	 */
	public static void out(HttpServletResponse response, Object object) {
		out(response, JSONUtil.beanToJson(object));
	}
}
