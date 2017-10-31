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
			// 通过产品id获得该产品对象
			Trip trip = tripDao.getTripById(id);
			// 初始化产品画廊的图片并加载到缓存
			tripDao.initTripPicture(trip.getTrippictures(),
					ServletActionContext.getServletContext().getRealPath("/"));

			HttpSession session = (HttpSession) request.getSession();
			// 把产品对象存到session中
			session.setAttribute("detail", trip);
			// 把产品评论总数存到session中
			session.setAttribute("num", commentDao.getCommentCount(id));

			// 如果当前用户已登录，则记录访问旅游详情页的时间和次数
			if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
				// 获取用户id
				User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
				// 添加详情页访问记录
				Globle.addAccessRecord(user.getId(), Constants.TRIP_DETAIL_PAGE);
			}
			return "showtripsuccess";
			
		}

		// 产品-评论分页查询
		if (type.equals("comment")) {
			// 获取要显示的页码数
			HttpServletResponse response = (HttpServletResponse)  
					ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE); 
			String pageStr = request.getParameter("page");
			Integer page = Utils.getPageNum(pageStr);
			
			// 获取指定页的评论集合
			List<Comment> comments = commentDao.getCommentsByTripId(id, page, Constants.COMMENT_DETAIL_MAX);
			// 初始化评论图片
			for (Comment c : comments) {
				Set<Commentpicture> pictures = c.getCommentpictures();
				commentDao.initCommentPicture(pictures, ServletActionContext.getServletContext()
						.getRealPath("/"));
			}
			ControllerUtil.out(response, comments);
		}

		// 更新评论的赞或踩数量
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
