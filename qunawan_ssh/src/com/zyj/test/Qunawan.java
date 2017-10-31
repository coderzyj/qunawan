package com.zyj.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zyj.entity.City;
import com.zyj.entity.Orders;
import com.zyj.service.OrderService;
import com.zyj.serviceImp.OrderServiceImp;

public class Qunawan {
	AbstractApplicationContext cxt;
	SessionFactory sf;
	
	@Before
	public void init(){
		cxt = new ClassPathXmlApplicationContext("bean.xml");
		sf=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 

	}
	@Test
	public void test() {
		Session session=sf.openSession();
		City city=(City) session.get(City.class,118);
		System.out.println(city.getName());
		Set<City> c=city.getCities_1();
		Iterator<City> i=c.iterator();
		while(i.hasNext())
		{
			City c1=i.next();
			System.out.println(c1.getName());
		}
	}
	@Test
	public void test1() {
		
		OrderService us=(OrderService) cxt.getBean("orderServiceImp");
		List<Orders> l=us.getOrders(1, 1, 10);
		for(Orders o:l)
		{
			System.out.println(o.getNum());
		}
	}
}
