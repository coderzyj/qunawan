package com.zyj.serviceImp;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;



import com.zyj.dao.CommentDao;
import com.zyj.dao.CommentPicDao;
import com.zyj.entity.Comment;
import com.zyj.entity.Commentpicture;
import com.zyj.entity.Sequence;
import com.zyj.service.CommentService;
import com.zyj.utils.Utils;
@Service
@Transactional
public class CommentServiceImp implements CommentService{
	private CommentDao dao;
	private CommentPicDao commentPicDao;
	@Resource(name="commentPicDaoImp")
	public void setCommentPicDao(CommentPicDao commentPicDao) {
		this.commentPicDao = commentPicDao;
	}

	@Resource(name="commentDaoImp")
	public void setDao(CommentDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Comment> getCommentsPerPage(Integer userId, Integer sq_id,
			Integer page, Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCommentCount(Integer tripId) {
		// TODO Auto-generated method stub
		return dao.getCommentCount(tripId);
	}

	@Override
	public Integer updateUseful(Integer commentId, Boolean isUseful) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getAvg_Score(String type, Integer tripId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Float[]> getCountByAvg(Integer tripId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getCommentsByTripId(Integer tripId, Integer page,
			Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCommentedCountByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCommentedCountByTrip(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Comment com, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initCommentPicture(Set<Commentpicture> pictures, String basePath) {
		// TODO Auto-generated method stub
		if (pictures == null || pictures.size() == 0) {
			return;
		}
		for (Commentpicture cp : pictures) {
			String path = basePath + "image_cache\\" + cp.getName();
			if (!new File(path).exists()) {
				Utils.getFile(cp.getData(), path);
			}
		}
	}

	@Override
	public void saveComment(Comment comment, List<byte[]> byteArray,
			Sequence sq_commented) {
		// TODO Auto-generated method stub
		int com_id = dao.save(comment);
		
		/**
		 * 2、存储评论图片集
		 */
		if(byteArray != null){
			for (byte[] bs : byteArray) {
				Commentpicture comPic = new Commentpicture();
				Comment co=new Comment();
				co.setId(com_id);
				comPic.setComment(co);
				comPic.setData(bs);
				comPic.setName(createName() + ".jpg");
				commentPicDao.save(comPic, con);
			}
		}
		
		/**
		 * 3、更新行程评分
		 */
		//评论对应行程id
		int trip_id = comment.getOrders().getTrip().getId();
		//五星评论数（好评数）
		float good_count=0;
		//获取该产品的各星级对应的评论数列表
		List<Float[]> data = dao.getCountByAvg(trip_id);
		//总评论数，如果没有则设置为1，防止分母为0的异常
		float total_count=data.size()==0?1:data.size();
		for(Float[] avg_score : data){
			//取星级近似值
			int star = Math.round(avg_score[0]);
			if(star == 5)
				good_count += avg_score[1];
		}
		//计算好评率
		float good_rate = (float)good_count/total_count*100;
		
		float place_score = dao.getAvg_Score("place", trip_id, con);
		float hotel_score = dao.getAvg_Score("hotel", trip_id, con);
		float service_score = dao.getAvg_Score("service", trip_id, con);
		float traffic_score = dao.getAvg_Score("traffic", trip_id, con);
		
		comment.getTrip().setGood_rate(good_rate);
		comment.getTrip().setPlace_score(place_score);
		comment.getTrip().setService_score(service_score);
		comment.getTrip().setHotel_score(hotel_score);
		comment.getTrip().setTraffic_score(traffic_score);					
		tripDao.updateScore(comment.getTrip(), con);
		
		/**
		 * 4、更新订单状态
		 */
		ordersDao.updateOrderState(sq_commented, comment.getOrders().getId(),con);
	}
	private String createName() {
		return (int) (Math.random() * 10241314) + "" + (int) (Math.random() * 10241314);
	}
}
