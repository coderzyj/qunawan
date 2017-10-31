package com.zyj.form;

import java.util.List;

import com.zyj.entity.Contact;



public class OrderDetailForm {

	// 联系�?
	private Contact contact_one;
	// 游客
	private List<Contact> contact_many;
	// 当前订单信息
	private OrderForm order;

	public Contact getContact_one() {
		return contact_one;
	}

	public void setContact_one(Contact contact_one) {
		this.contact_one = contact_one;
	}

	public List<Contact> getContact_many() {
		return contact_many;
	}

	public void setContact_many(List<Contact> contact_many) {
		this.contact_many = contact_many;
	}

	public OrderForm getOrder() {
		return order;
	}

	public void setOrder(OrderForm order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetailForm [contact_one=" + contact_one + ", contact_many=" + contact_many + ", order=" + order
				+ "]";
	}

}
