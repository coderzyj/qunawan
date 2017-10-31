package com.zyj.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;




import com.opensymphony.xwork2.ActionSupport;
import com.zyj.entity.Orders;
import com.zyj.entity.User;
import com.zyj.form.OrderDetailForm;
import com.zyj.form.OrderForm;
import com.zyj.globle.Constants;
import com.zyj.service.OrderService;
import com.zyj.utils.Utils;

@Controller("orderaction")
@Scope("prototype")
public class OrderAction extends ActionSupport{
	
	private OrderService orderdao;
	private HttpServletRequest request;
	@Resource(name="orderServiceImp")
	public void setOrderdao(OrderService orderdao) {
		this.orderdao = orderdao;
	}
	public String showDetailorder()
	{
		request=ServletActionContext.getRequest();
		String id = request.getParameter("order_id");

		Integer order_id = Integer.parseInt(id);

		OrderDetailForm orderDetailForm = new OrderDetailForm();

		orderDetailForm.setContact_many(orderdao.getContactsByOrderId(order_id));
		orderDetailForm.setContact_one(orderdao.getContactByOrderId(order_id));
		
		Orders order=orderdao.getOrderById(order_id);
		OrderForm of=new OrderForm();
		of.setOrderid(order.getId());
		of.setContent(order.getTrip().getTitle());
		of.setOrderno(order.getOrderNo());
		of.setCreate_time(order.getStartTime().toString());
		of.setStart_time(order.getStartTime().toString());
		of.setPerson_num(order.getNum());
		of.setPrice(order.getTotalPrice());
		of.setTotalDays(order.getTrip().getTime());
		of.setState(order.getSequence());
		of.setUser(order.getUser());
		orderDetailForm.setOrder(of);
		request.getSession().setAttribute("orderDetailForm", orderDetailForm);
		return "showdetailsuccess";
	}

	public String showMyorder()
	{
		
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		request=ServletActionContext.getRequest();
		List<OrderForm> orderForms = getOrders(u.getId());
		request.setAttribute("orderFormList", orderForms);
		request.setAttribute("pageCount", orderForms .size() != 0 ? orderForms.get(0).getPageCount() : 1);
		request.setAttribute("cur", Utils.getPageNum(request.getParameter("page")));
		return "showordersuccess";
	}
	private List<OrderForm> getOrders(Integer userId) {
		// 接收要查询的订单页码
		String pageStr = request.getParameter("page");
		// 类型转换
		int page = Utils.getPageNum(pageStr);
		// 获取订单的总数目
		int orderCount = orderdao.getOrdersbyUser(userId);
		// 计算列表的总页码
		int pageCount = (orderCount % Constants.ORDER_MAX == 0) ? (orderCount / Constants.ORDER_MAX)
				: (orderCount / Constants.ORDER_MAX + 1);
		// 获取查询页的订单集合
		List<Orders> orders = orderdao.getOrders(userId, page, Constants.ORDER_MAX);
		// 将查询的订单集合进行重新封装处理
		List<OrderForm> orderForms = new ArrayList<OrderForm>();
		for (Orders order : orders) {
			OrderForm orderForm = new OrderForm();
			orderForm.setOrderid(order.getId());
			orderForm.setContent(order.getTrip().getTitle());
			orderForm.setCreate_time(order.getCreateTime().toString());
			orderForm.setOrderno(order.getOrderNo());
			orderForm.setPerson_num(order.getNum());
			orderForm.setPrice(order.getTotalPrice());
			orderForm.setStart_time(order.getStartTime().toString());
			orderForm.setState(order.getSequence());
			orderForm.setPageCount(pageCount);
			orderForm.setTotalDays(order.getTrip().getTime());
			orderForms.add(orderForm);
		}
		return orderForms;
	}
}
