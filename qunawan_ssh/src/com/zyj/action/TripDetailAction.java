package com.zyj.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;









import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyj.entity.Comment;
import com.zyj.entity.Commentpicture;
import com.zyj.entity.Trip;
import com.zyj.entity.User;
import com.zyj.globle.Constants;
import com.zyj.globle.Globle;
import com.zyj.service.CommentService;
import com.zyj.service.TripService;
import com.zyj.utils.ControllerUtil;
import com.zyj.utils.Utils;

@Controller("tripdetailaction")
@Scope("prototype")
public class TripDetailAction extends ActionSupport{
	private TripService tripDao;
	private CommentService commentDao;
	@Resource(name="commentServiceImp")
	public void setCommentDao(CommentService commentDao) {
		this.commentDao = commentDao;
	}


	@Resource(name="tripServiceImp")
	public void setTripDao(TripService tripDao) {
		this.tripDao = tripDao;
	}


	public String showTripdetail()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String type = request.getParameter("type");
		String tmp_id = request.getParameter("id");
		int id=Integer.parseInt(tmp_id);
		//HttpSession session=request.getSession();
		if (type.equals("init")) {
			// ͨ����Ʒid��øò�Ʒ����
			Trip trip = tripDao.getTripById(id);
			// ��ʼ����Ʒ���ȵ�ͼƬ�����ص�����
			tripDao.initTripPicture(trip.getTrippictures(),
					ServletActionContext.getServletContext().getRealPath("/"));

			HttpSession session = (HttpSession) request.getSession();
			// �Ѳ�Ʒ����浽session��
			session.setAttribute("detail", trip);
			// �Ѳ�Ʒ���������浽session��
			session.setAttribute("num", commentDao.getCommentCount(id));

			// �����ǰ�û��ѵ�¼�����¼������������ҳ��ʱ��ʹ���
			if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
				// ��ȡ�û�id
				User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
				// �������ҳ���ʼ�¼
				Globle.addAccessRecord(user.getId(), Constants.TRIP_DETAIL_PAGE);
			}
			return "showtripsuccess";
			
		}

		// ��Ʒ-���۷�ҳ��ѯ
		if (type.equals("comment")) {
			// ��ȡҪ��ʾ��ҳ����
			HttpServletResponse response = (HttpServletResponse)  
					ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE); 
			String pageStr = request.getParameter("page");
			Integer page = Utils.getPageNum(pageStr);
			
			// ��ȡָ��ҳ�����ۼ���
			List<Comment> comments = commentDao.getCommentsByTripId(id, page, Constants.COMMENT_DETAIL_MAX);
			// ��ʼ������ͼƬ
			for (Comment c : comments) {
				Set<Commentpicture> pictures = c.getCommentpictures();
				commentDao.initCommentPicture(pictures, ServletActionContext.getServletContext()
						.getRealPath("/"));
			}
			ControllerUtil.out(response, comments);
		}

		// �������۵��޻������
		if (type.equals("goodOrBad")) {
			HttpServletResponse response = (HttpServletResponse)  
					ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE); 
				String isUsefull_tmp = request.getParameter("isUsefull");
				boolean isUsefull = Boolean.parseBoolean(isUsefull_tmp);
				ControllerUtil.out(response, "" + commentDao.updateUseful(id, isUsefull));
		}
		return null;
	}
}
