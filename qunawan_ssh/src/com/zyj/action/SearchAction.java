package com.zyj.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;










import com.opensymphony.xwork2.ActionSupport;
import com.zyj.entity.Trip;
import com.zyj.entity.User;
import com.zyj.form.SearchBean;
import com.zyj.form.SearchForm;
import com.zyj.globle.Constants;
import com.zyj.globle.Globle;
import com.zyj.service.TripService;
@Controller("searchaction")
@Scope("prototype")
public class SearchAction extends ActionSupport{
	HttpServletRequest request;
	private TripService tripService;
	@Resource(name="tripServiceImp")
	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}

	public String Search(){
		request=ServletActionContext.getRequest();
		String type = request.getParameter("triptype"); // 行程类型
		String place = request.getParameter("place"); // 所属地区
		String start_place = request.getParameter("start_place"); // 出发地
		String theme = request.getParameter("theme"); // 主题
		String traffic = request.getParameter("traffic"); // 交通工具
		String time_str = request.getParameter("time"); // 出行天数
		String min_price_str = request.getParameter("min_price"); // 最低价格
		String max_price_str = request.getParameter("max_price"); // 最高价格

		String price_sort = request.getParameter("price_sort"); // 价格排序规则
		String good_rate_sort = request.getParameter("good_rate_sort"); // 好评率排序规则
		String cur_sort_str = request.getParameter("cur_sort_str"); // 当前排序规则

		String search_key = request.getParameter("search_key"); // 搜索关键字

		String pageCurrent_str = request.getParameter("pageCurrent"); // 当前页码

		/* 把无效参数置为null，有效参数去除首尾空格 */
		type = transString(type);
		place = transString(place);
		start_place = transString(start_place);
		theme = transString(theme);
		traffic = transString(traffic);
		price_sort = transString(price_sort);
		good_rate_sort = transString(good_rate_sort);
		cur_sort_str = transString(cur_sort_str);
		search_key = transString(search_key);

		/* 行程天数和价格区间进行类型转换 */
		Integer time;
		Integer min_price;
		Integer max_price;
		Integer pageCurrent;
		try {
			time = Integer.parseInt(time_str);
		} catch (Exception e) {
			time = null;
		}
		try {
			min_price = Integer.parseInt(min_price_str);
		} catch (Exception e) {
			min_price = null;
		}
		try {
			max_price = Integer.parseInt(max_price_str);
		} catch (Exception e) {
			max_price = null;
		}
		try {
			pageCurrent = Integer.parseInt(pageCurrent_str);
		} catch (Exception e) {
			pageCurrent = 1;
		}

		// 实例化一个页面筛选表单
		SearchForm vo = new SearchForm(type, place, start_place, theme, traffic, time, min_price, max_price,
				pageCurrent, price_sort, good_rate_sort, cur_sort_str, search_key);

		// 获取搜索页面显示的行程列表
		List<Trip> list = tripService.getSearchTripsByVo(vo);
		// 获取搜索页面其余部分的内容（检索选项列表、分页页码、页面表单内容……）
		SearchBean bean = tripService.getSearchBean(vo);

		// 初始化图片缓存
		for (Trip t : list) {
			tripService.initTripPicture(t.getTrippictures(), ServletActionContext.getServletContext().getRealPath("/"));
		}

		request.getSession().setAttribute(Constants.SEARCH_LIST, list);
		request.getSession().setAttribute(Constants.SEARCH_BEAN, bean);

		// 如果当前用户已登录，则记录访问搜索页的时间和次数
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			// 获取用户id
			User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
			// 添加搜索页访问记录
			Globle.addAccessRecord(user.getId(), Constants.SEARCH_PAGE);
		}

		return "searchsuccess";
	}
	private boolean isEmpty(String s) {
		return s == null || "".equals(s) ? true : false;
	}

	/**
	 * 字符串转换，有效串去除空格，无效串置为null
	 * 
	 * @param s
	 *            待转换字符串
	 * @return 转换后的字符串
	 */
	private String transString(String s) {
		if (isEmpty(s))
			return null;
		return s.trim();
	}
}
