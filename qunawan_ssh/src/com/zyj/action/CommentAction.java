package com.zyj.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;











import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyj.entity.Comment;
import com.zyj.entity.Sequence;
import com.zyj.entity.User;
import com.zyj.form.FinishedCommentForm;
import com.zyj.globle.Constants;
import com.zyj.globle.Globle;
import com.zyj.service.CommentService;
import com.zyj.service.OrderService;
import com.zyj.service.SequenceService;
import com.zyj.utils.ControllerUtil;
import com.zyj.utils.Utils;



@Controller("commentaction")
@Scope("prototype")
public class CommentAction extends ActionSupport{
	HttpServletRequest request;
	private int userId;
	private int orderId;
	private SequenceService sequenceDao;
	private OrderService ordersDao;
	private CommentService commentDao;
	
	@Resource(name="commentServieImp")
	public void setCommentDao(CommentService commentDao) {
		this.commentDao = commentDao;
	}

	@Resource(name="orderServieImp")
	public void setOrdersDao(OrderService ordersDao) {
		this.ordersDao = ordersDao;
	}

	@Resource(name="sequenceServieImp")
	public void setSequenceDao(SequenceService sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

	public String showComment()
	{
		String type = request.getParameter("type");
		String pageStr = request.getParameter("page");

		User user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		userId = user.getId();

		// 页面初始化
		if (Constants.COMMENT_INIT.equals(type)) {
			/*dispatcher = request.getRequestDispatcher("/WEB-INF/pages/personal/personal_myComments.jsp");
			dispatcher.forward(request, response);*/
			return "back";
		}

		Sequence sq_to_comment = sequenceDao.getSeqByKeyAndType(Constants.WAIT_COMMENT_ORDER_STATE,
				Constants.ORDER_TYPE);
		Sequence sq_commented = sequenceDao.getSeqByKeyAndType(Constants.COMMENTED_ORDER_STATE, Constants.ORDER_TYPE);

		int waitCount = ordersDao.getOrdersbyUser(user.getId(), sq_to_comment);
		int finishCount = commentDao.getCommentedCountByUser(user.getId());

		// 获取待评论的订单数据
		if (Constants.COMMENT_GET_WAIT_COMMENTS.equals(type)) {
			getWaitComments(pageStr, waitCount, finishCount, sq_to_comment, sq_commented);
		}
		// 获取已经评论的评论数据
		if (Constants.COMMENT_GET_FINISH_COMMENT.equals(type)) {
			getFinishComment(pageStr, sq_commented.getId(), finishCount);
		}
		// 提交评论
		if (Constants.COMMENT_SUBMIT_COMMENT.equals(type)) {
			submitComment();
		}
		return null;
	}
	private void submitComment() throws ServletException, IOException {

		Comment comment = analysisComment();

		// 四项评分，一项评论内容，一个图片集
		if (comment == null) {
			request.setAttribute(Constants.COMMENT_INFO, "评论失败");
			/*dispatcher = request.getRequestDispatcher("/WEB-INF/pages/personal/personal_myComments.jsp");
			dispatcher.forward(request, response);*/
			return;
		}

		List<byte[]> byteArray = null;
		byteArray = Globle.getPics(userId);
		Sequence sq_commented = sequenceDao.getSeqByKeyAndType(Constants.COMMENTED_ORDER_STATE, Constants.ORDER_TYPE);

		// 保存comment + commentpic
		commentDao.saveComment(comment, byteArray, sq_commented);

		request.setAttribute(Constants.COMMENT_INFO, "评论成功");
		/*dispatcher = request.getRequestDispatcher("/WEB-INF/pages/personal/personal_myComments.jsp");
		dispatcher.forward(request, response);*/
		
		// 清除Globle中缓存
		Globle.clear(userId);
	}

	/**
	 * 获取已评论的订单评论
	 * 
	 * @param pageStr
	 *            当前页码
	 * @param sq_commented
	 *            已点评的序列ID
	 * @param finishCount
	 *            评论总数
	 */
	private void getFinishComment(String pageStr, Integer sq_id, int finishCount) {
		HttpServletResponse response = (HttpServletResponse)  
				ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);  

		int page = Utils.getPageNum(pageStr);
		// 页码总数
		int pageCount = (int) Math.ceil((double)finishCount/Constants.COMMENT_MAX);
		// 获取当前用户已经评的列表
		List<Comment> comments = commentDao.getCommentsPerPage(userId, sq_id, page, Constants.COMMENT_MAX);
		
		List<FinishedCommentForm> results = new ArrayList<FinishedCommentForm>();
		// 把每个评论对象的属性封装到用于页面显示的表单集合中
		for (Comment comment : comments) {
			FinishedCommentForm fcf = new FinishedCommentForm(comment, pageCount, finishCount);

			// 更新缓存的评论图片
			if (comment.getCommentpictures() != null)
				commentDao.initCommentPicture(comment.getCommentpictures(), ServletActionContext.getServletContext()
						.getRealPath("/"));

			results.add(fcf);
		}
		ControllerUtil.out(response, results);
	}

	/**
	 * 获取待评论的订单列表
	 * 
	 * @param pageStr
	 *            当前页码
	 * @param waitCount
	 *            待评论总数量
	 * @param finishCount
	 *            已评论总数量
	 * @param sq_to_comment
	 *            带评论的序列ID
	 */
	private void getWaitComments(String pageStr, int waitCount, int finishCount,
			Sequence sq_to_comment, Sequence sq_commented) {
		int page = Utils.getPageNum(pageStr);
		// 总页码数
		int pageCount = (int) Math.ceil((double) waitCount / Constants.COMMENT_MAX);

		// 获取该用户待评价的订单集合
		List<Orders> orderList = ordersDao.getOrders(userId, sq_to_comment, page, Constants.COMMENT_MAX);

		List<WaitCommentForm> formList = new ArrayList<WaitCommentForm>();
		// 把每个订单对象的属性封装到用于页面显示的表单集合中
		for (Orders order : orderList) {
			WaitCommentForm wco = new WaitCommentForm(order, pageCount, waitCount, finishCount,
					commentDao.getCommentedCountByTrip(order.getTrip().getId()));

			tripService.initTripPicture(order.getTrip().getPic_list(), this.getServletConfig().getServletContext()
					.getRealPath("/"));
			formList.add(wco);
		}
		if (orderList.size() == 0) {
			WaitCommentForm wco = new WaitCommentForm(waitCount, finishCount);
			formList.add(wco);
		}
		ControllerUtil.out(response, formList);
	}

	/**
	 * 解析request请求，获取表单域数据
	 */
	private Comment analysisComment() {
		Comment comment = new Comment();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = null;

		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		if (items == null)
			return null;

		String commentText = null;
		// 解析request请求
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			// 如果是表单域 ，就是非文件上传元素
			if (item.isFormField()) {

				// 获取name属性的值
				String name = item.getFieldName();

				// 获取value属性的值
				String value = item.getString();

				if ("orderid".equals(name)) {
					orderId = Integer.parseInt(value);
				}
				if ("siteScore".equals(name)) {
					String siteScoreStr = ("".equals(value) || null == value) ? "1" : value;
					int siteScore = Integer.parseInt(siteScoreStr);
					comment.setPlace(siteScore);
				}
				if ("hotelScore".equals(name)) {
					String hotelScoreStr = ("".equals(value) || null == value) ? "1" : value;
					int hotelScore = Integer.parseInt(hotelScoreStr);
					comment.setHotel(hotelScore);
				}
				if ("serviceScore".equals(name)) {
					String serviceScoreStr = ("".equals(value) || null == value) ? "1" : value;
					int serviceScore = Integer.parseInt(serviceScoreStr);
					comment.setService(serviceScore);
				}
				if ("trafficScore".equals(name)) {
					String trafficScoreStr = ("".equals(value) || null == value) ? "1" : value;
					int trafficScore = Integer.parseInt(trafficScoreStr);
					comment.setTraffic(trafficScore);
				}
				if ("content".equals(name)) {
					commentText = ("".equals(value) || null == value) ? "没有任何评论" : value;
				}
			}
		}

		try {
			if (commentText != null && !"".equals(commentText)) {
				commentText = new String(commentText.getBytes("ISO8859_1"), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 获取待评论的订单对象
		Orders order = ordersDao.getOrderById(orderId);
		comment.setOrders(order);
		comment.setContent(commentText);
		comment.setTime(new Timestamp(new java.util.Date().getTime()));
		comment.setUser(new User(userId));
		comment.setTrip(order.getTrip());
		return comment;
	}
}
