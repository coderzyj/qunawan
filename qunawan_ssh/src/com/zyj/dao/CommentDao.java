package com.zyj.dao;

import java.sql.Connection;
import java.util.List;

import com.zyj.entity.Comment;



public interface CommentDao {
	/**
	 * ��ȡĳ�û��ѵ��������ۼ�
	 * @param userId �û�id
	 * @param sq_id ����״̬����id
	 * @param firstResult ��ҳ��ʼλ��
	 * @return ���ۼ���
	 */
	List<Comment> getCommentsPerPage(Integer userId, Integer sq_id, Integer page, Integer max);
	
	/**
	 * ��ȡ��Ʒ���ϵĳ���
	 * @param tripId ��Ʒid
	 * @return ���ϳ���
	 */
	Integer getCommentCount(Integer tripId);
	
	/**
	 * �����û�������Ϣ
	 * @param commentId ����id
	 * @param isUseful �Ƿ����
	 * @return ������/�ȵĸ���
	 */
	Integer updateUseful(Integer commentId, Boolean isUseful);

	/**
	 * �г̵���ƽ����
	 * type���������ֵ��������� 
	 */
	Float getAvg_Score(String type, Integer tripId, Connection con) ;

	/**
	 * ���ա�ƽ���֡�-�����۸������ķ�ʽ��ѯ��������
	 */
	List<Float[]> getCountByAvg(Integer tripId, Connection con) ;
	
	/**
	 * ����tripid��ҳ��ѯComment����������ҳ�������Բ��ַ�װ�������Packager��
	 */
	List<Comment> getCommentsByTripId(Integer tripId, Integer page, Integer max);
	
	/**
	 * ��ȡ�û���������
	 */
	Integer getCommentedCountByUser(Integer userId);
	
	/**
	 * ��ȡ�г̵�������
	 */
	Integer getCommentedCountByTrip(Integer tripId);
	
	/**
	 * �洢���۶��󣬷�������
	 */
	Integer save(Comment com);

}
