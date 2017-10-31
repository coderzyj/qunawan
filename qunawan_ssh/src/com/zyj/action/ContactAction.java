package com.zyj.action;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;








import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyj.dao.OrderContactDao;
import com.zyj.entity.Contact;
import com.zyj.entity.User;
import com.zyj.form.ContactForm;
import com.zyj.globle.Constants;
import com.zyj.service.ContactService;
import com.zyj.utils.ControllerUtil;
import com.zyj.utils.Utils;
@Controller("contactaction")
@Scope("prototype")
public class ContactAction extends ActionSupport{
	HttpServletRequest request;
	private ContactService contactDao;
	private OrderContactDao orderContactDao;
	
	@Resource(name="orderContactDaoImp")
	public void setOrderContactDao(OrderContactDao orderContactDao) {
		this.orderContactDao = orderContactDao;
	}
	@Resource(name="contactServiceImp")
	public void setContactDao(ContactService contactDao) {
		this.contactDao = contactDao;
	}
	public String updateContact()
	{
	
		System.out.println("updateContact执行");
		HttpServletResponse response = (HttpServletResponse)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);  
		request=ServletActionContext.getRequest();
		String contactId = request.getParameter("c_id");
		if (contactId == null || "".equals(contactId))
			return null;
		int id = Integer.parseInt(contactId);
		Contact contact = contactDao.getContactById(id);



		try {
			contactDao.updateContact(getContactFromRequest(contact));
			ControllerUtil.out(response, "更新成功！");
		} catch (Exception e) {
			ControllerUtil.out(response, "更新失败！");
			e.printStackTrace();
		}
		return null;
	}
	private Contact getContactFromRequest(Contact contact) throws ServletException, IOException {
		
		String c_name = request.getParameter("c_name");
		String c_sexStr = request.getParameter("c_sex");
		String c_phone = request.getParameter("c_mobile");
		String c_email = request.getParameter("c_email");
		String c_year = request.getParameter("c_sel_year");
		String c_month = request.getParameter("c_sel_month");
		String c_day = request.getParameter("c_sel_day");
		String c_cardno = request.getParameter("c_cardno");
		System.out.println(c_name+","+c_sexStr+","+c_phone+","+c_email+","+c_year+","+c_month+","+c_day+","+c_cardno);
		boolean c_sex = "1".equals(c_sexStr) ? true : false;
		
		Date birthday;
		if (c_year == null || "".equals(c_year) || "0".equals(c_year)) {
			birthday = null;
		} else {
			if (Integer.parseInt(c_month) < 10)
				c_month = "0" + c_month;

			if (Integer.parseInt(c_day) < 10)
				c_day = "0" + c_day;
			birthday = Utils.stringToDate(c_year + c_month + c_day);
		}
		if (!validateData(c_name, c_phone, c_cardno)) {
			request.setAttribute(Constants.ERROR, "信息不完整,更新失败");
			/*request.getRequestDispatcher("/WEB-INF/pages/personal.jsp").forward(request, response);*/
		}

		contact.setBirthday(birthday);
		contact.setName(c_name);
		contact.setPhone(c_phone);
		contact.setSex(c_sex);
		contact.setCardno(c_cardno);
		contact.setEmail(c_email);

		return contact;
	}
	public String showContact()
	{
		request=ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		String pageStr = request.getParameter("page");
		int page = Utils.getPageNum(pageStr);

		int contactNum = contactDao.getAllContactByUser(user.getId()).size();
		int pageCount = (int) Math.ceil((float) contactNum / Constants.CONTACT_MAX);
		if (pageCount == 0)
			pageCount = 1;

		List<Contact> contacts = contactDao.getAllContactPerPage(user.getId(), page, Constants.CONTACT_MAX);
		List<ContactForm> formList = new ArrayList<ContactForm>();
		for (Contact contact : contacts) {
			ContactForm contactForm = new ContactForm(contact, pageCount);
			formList.add(contactForm);
		}
		if (formList.size() != 0)
			request.setAttribute("pageCount", formList.get(0).getPageCount());
		request.setAttribute("formList", formList);
		request.setAttribute("cur", Utils.getPageNum(request.getParameter("page")));
		return "showcontactsuccess";
	}

	
	public String deletecontact()
	{
		HttpServletResponse response = (HttpServletResponse)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);  
		request=ServletActionContext.getRequest();
		String c_contactid = request.getParameter("c_id");
		if (c_contactid == null)
			return null;
		int id = Integer.parseInt(c_contactid);

		// 直接删除，由于外键关联会报错
		
		int count = orderContactDao.getOrderContactsCount(id);
		if (count == 0) {
			contactDao.delete(id);
			ControllerUtil.out(response, Constants.OP_SUCCESS);
		} else
			ControllerUtil.out(response, Constants.OP_FAIL);
	
			
		return null;
	}
	
	public String addcontact()
	{
		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
	
		User u=(User) session.getAttribute("user");
		HttpServletResponse response = (HttpServletResponse)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);  
		Contact contact;
		try {
			contact = getContactFromRequest(new Contact());
			contactDao.saveContact(contact,u.getId());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ControllerUtil.out(response, Constants.OP_SUCCESS);
		return null;
	}
	private boolean validateData(String name, String phone, String real_name) {
		if ("".equals(name) || "".equals(phone) || "".equals(real_name))
			return false;
		if (name == null || phone == null || real_name == null)
			return false;

		return true;
	}
}
