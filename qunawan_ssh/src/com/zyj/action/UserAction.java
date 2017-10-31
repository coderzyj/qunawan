package com.zyj.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;









import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyj.dao.CityDao;
import com.zyj.entity.City;
import com.zyj.entity.User;
import com.zyj.globle.Constants;
import com.zyj.service.UserService;
import com.zyj.utils.Utils;
@Controller("useraction")
@Scope("prototype")
public class UserAction extends ActionSupport{
	private User user=new User();
	List<City> provinces;
	private UserService us;
	private CityDao cd;
	
	public CityDao getCd() {
		return cd;
	}
	@Resource(name="cityDaoImp")
	public void setCd(CityDao cd) {
		this.cd = cd;
	}
	public UserService getUs() {
		return us;
	}
	@Resource(name="userServiceImp")
	public void setUs(UserService us) {
		this.us = us;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	

	public List<City> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<City> provinces) {
		this.provinces = provinces;
	}
	public String login()
	{
		User u=us.Login(user);
		if(u==null)
			return "loginfailed";
		else{
			
			HttpSession session=ServletActionContext.getRequest().getSession();
			u.setPassword(user.getPassword());
			session.setAttribute("user", u);	
			System.out.println(u.getName()+","+u.getPhone());
			return "loginsuccess";
		}
	}
	public String register()
	{
		if(us.Register(user))
			return "regissuccess";
		else
		{
			ServletActionContext.getRequest().setAttribute("init", "reg");
			return "regisfail";
		}
	}
	public String logout()
	{
		ServletActionContext.getRequest().getSession().invalidate();
		return "logoutsuccess";
	}
	public String ShowInfo()
	{
		provinces=cd.getAllProvinces();
		ServletActionContext.getRequest().getSession().setAttribute("provinces", provinces);
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		System.out.println(u.getName()+","+u.getPhone()+"1111111122222222"+u.getPassword());
		return "showinfosuccess";
	}
	public String updateInfo()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = (HttpServletResponse)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE); 
		String name = request.getParameter("name");
		String sexStr = request.getParameter("sex");
		String phone = request.getParameter("mobile");
		String email = request.getParameter("email");
		String real_name = request.getParameter("realname");
		String year = request.getParameter("sel_year");
		String month = request.getParameter("sel_month");
		String day = request.getParameter("sel_day");
		String cityStr = request.getParameter("city");
		// 对提交的信息进行空字符验证
		if (!validateData(email, phone, sexStr)) {
			// 设置错误信息
			request.setAttribute(Constants.ERROR, "基本信息不完整,更新失败");
			
			return "updatefail";
		}
		// 对提交的信息进行手机和邮箱唯一性验证
		if (!us.checkPhoneAndEmail(phone, email, user)) {
			request.setAttribute(Constants.ERROR, "邮箱或者手机已存在");

			return "updatefail";
		}
		// 对表单提交的数据进行封装处理
		boolean sex = "0".equals(sexStr) ? false : true;
		Date birthday;
		City city;
		if (year == null || "".equals(year) || "0".equals(year)) {
			birthday = null;
		} else {
			if (Integer.parseInt(month) < 10)
				month = "0" + month;

			if (Integer.parseInt(day) < 10)
				day = "0" + day;
			birthday = Utils.stringToDate(year + month + day);
		}
		if (cityStr == null || "".equals(cityStr)) {
			city = null;
		} else {
			city = cd.getCityById(Integer.parseInt(cityStr));
		}
		// 将数据封装到user对象
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRealName(real_name);
		user.setSex(sex);
		user.setCity(city);
		user.setBirthday(birthday);

		us.Update(user);
		// 更新后的数据保存进session
		request.getSession().setAttribute(Constants.USER_KEY, user);

		// 提示信息更新成功
		request.setAttribute(Constants.ERROR, "更新成功");
		
		return "updatesuccess";
	}
	private boolean validateData(String email, String phone, String sex) {
		if ("".equals(email) || "".equals(phone) || "".equals(sex))
			return false;

		if (email == null || phone == null || sex == null)
			return false;

		return true;
	}
	public String jump()
	{
		
		return "jumpsuccess";
	}
	public String updatepwd()
	{
		User u=(User)ServletActionContext.getRequest().getSession().getAttribute("user");
		String oldPassword = ServletActionContext.getRequest().getParameter("oldPassWord");
		String newPassword = ServletActionContext.getRequest().getParameter("newPassWord");
		if(!oldPassword.equals(u.getPassword())){
			ServletActionContext.getRequest().setAttribute(Constants.ERROR, "密码不正确");
			return "updatepwdfail";
		}
		u.setPassword(Utils.toMD5(newPassword));
		us.Update(u);
		ServletActionContext.getRequest().setAttribute(Constants.ERROR, "密码更新成功");
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "updatepwdsuccess";
	}
}
