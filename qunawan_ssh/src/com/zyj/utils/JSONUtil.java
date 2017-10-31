package com.zyj.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * json����, ����json <--> java��ת
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JSONUtil {

	/**
	 * ע���������ַ���תjava
	 */
	private static void registerDate2Java() {
		JSONUtils.getMorpherRegistry()
				.registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
	}

	/**
	 * java��ת��json
	 * @param object
	 * @return
	 */
	public static String beanToJson(Object object) {
		String jsonString = null;
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		// ����ת����ʽ, ���yyyy-MM-dd�ַ���
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {

			@Override
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return processValue(value);
			}

			@Override
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return processValue(value);
			}

			private Object processValue(Object value) {
				if (value != null) {
					// ��ʽ������
					return DateFormatUtils.format((Date) value, "yyyy-MM-dd");
				}
				return "";
			}
		});
		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {
				// ����ת��
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			} else {
				// ����ת��
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}

	/**
	 * ����תjson
	 * @param coll
	 * @return
	 */
	public static <T> String listToJson(Collection<T> coll) {
		return beanToJson(coll);
	}

	/**
	 * ����תjson
	 * @param objects
	 * @return
	 */
	public static String arrayToJson(Object[] objects) {
		return beanToJson(objects);
	}

	/**
	 * jsonתjava��
	 * @param jsonString
	 * @param beanClass
	 * @return
	 */
	public static <T> Object jsonToBean(String jsonString, Class<T> beanClass) {
		registerDate2Java();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return JSONObject.toBean(jsonObject, beanClass);
	}

	/**
	 * jsonתjava�༯��
	 * @param jsonString
	 * @param beanClass
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonString, Class<T> beanClass) {
		registerDate2Java();
		JSONArray array = JSONArray.fromObject(jsonString);
		return (List<T>) JSONArray.toCollection(array, beanClass);
	}

	/**
	 * json -> java
	 * ����Timestampת���쳣
	 * @param jsonObject
	 * @param clazz
	 * @return
	 */
	public static Object toBean(JSONObject jsonObject, Class clazz) {
		JSONUtils.getMorpherRegistry().registerMorpher(new AbstractObjectMorpher() {

			@Override
			public Class morphsTo() {
				return Date.class;
			}

			@Override
			public boolean supports(Class clazz) {
				return String.class.isAssignableFrom(clazz);
			}

			@Override
			public Object morph(Object value) {
				if (value == null || StringUtils.isBlank(value.toString())) {
					return null;
				}

				if (Date.class.isAssignableFrom(value.getClass())) {
					return value;
				}

				if (!supports(value.getClass())) {
					try {
						throw new Exception(value.getClass() + " is not supported");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				String strValue = (String) value;

				SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return new Date(dateParser.parse(strValue.toLowerCase()).getTime());
				} catch (ParseException e) {
					return null;
				}
			}
		});
		return JSONObject.toBean(jsonObject, clazz);
	}
}
