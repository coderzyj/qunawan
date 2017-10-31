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
		String type = request.getParameter("triptype"); // �г�����
		String place = request.getParameter("place"); // ��������
		String start_place = request.getParameter("start_place"); // ������
		String theme = request.getParameter("theme"); // ����
		String traffic = request.getParameter("traffic"); // ��ͨ����
		String time_str = request.getParameter("time"); // ��������
		String min_price_str = request.getParameter("min_price"); // ��ͼ۸�
		String max_price_str = request.getParameter("max_price"); // ��߼۸�

		String price_sort = request.getParameter("price_sort"); // �۸��������
		String good_rate_sort = request.getParameter("good_rate_sort"); // �������������
		String cur_sort_str = request.getParameter("cur_sort_str"); // ��ǰ�������

		String search_key = request.getParameter("search_key"); // �����ؼ���

		String pageCurrent_str = request.getParameter("pageCurrent"); // ��ǰҳ��

		/* ����Ч������Ϊnull����Ч����ȥ����β�ո� */
		type = transString(type);
		place = transString(place);
		start_place = transString(start_place);
		theme = transString(theme);
		traffic = transString(traffic);
		price_sort = transString(price_sort);
		good_rate_sort = transString(good_rate_sort);
		cur_sort_str = transString(cur_sort_str);
		search_key = transString(search_key);

		/* �г������ͼ۸������������ת�� */
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

		// ʵ����һ��ҳ��ɸѡ��
		SearchForm vo = new SearchForm(type, place, start_place, theme, traffic, time, min_price, max_price,
				pageCurrent, price_sort, good_rate_sort, cur_sort_str, search_key);

		// ��ȡ����ҳ����ʾ���г��б�
		List<Trip> list = tripService.getSearchTripsByVo(vo);
		// ��ȡ����ҳ�����ಿ�ֵ����ݣ�����ѡ���б���ҳҳ�롢ҳ������ݡ�����
		SearchBean bean = tripService.getSearchBean(vo);

		// ��ʼ��ͼƬ����
		for (Trip t : list) {
			tripService.initTripPicture(t.getTrippictures(), ServletActionContext.getServletContext().getRealPath("/"));
		}

		request.getSession().setAttribute(Constants.SEARCH_LIST, list);
		request.getSession().setAttribute(Constants.SEARCH_BEAN, bean);

		// �����ǰ�û��ѵ�¼�����¼��������ҳ��ʱ��ʹ���
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			// ��ȡ�û�id
			User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
			// �������ҳ���ʼ�¼
			Globle.addAccessRecord(user.getId(), Constants.SEARCH_PAGE);
		}

		return "searchsuccess";
	}
	private boolean isEmpty(String s) {
		return s == null || "".equals(s) ? true : false;
	}

	/**
	 * �ַ���ת������Ч��ȥ���ո���Ч����Ϊnull
	 * 
	 * @param s
	 *            ��ת���ַ���
	 * @return ת������ַ���
	 */
	private String transString(String s) {
		if (isEmpty(s))
			return null;
		return s.trim();
	}
}
