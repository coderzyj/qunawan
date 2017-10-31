package com.zyj.tag;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.zyj.entity.Trip;



/**
 * ����ȷ��ҳ����ʾ������Ϣ�ı�ǩ�����з�������ֵ�ĺ������£�
 * 
 * EVAL_BODY_INCLUDE����Body������ڵ�������У�doStartTag()�������� EVAL_PAGE����������ҳ�棬doEndTag()�������� SKIP_BODY�����Զ�Body�Ĵ���doStartTag()��doAfterBody()��������
 * SKIP_PAGE�����Զ�����ҳ��Ĵ���doEndTag()�������� EVAL_BODY_TAG���Ѿ���ֹ����EVAL_BODY_BUFFEREDȡ��
 * 
 * EVAL_BODY_BUFFERED�����뻺��������setBodyContent()�����õ���BodyContent����������tag��body�� �����ʵ����BodyTag����ôdoStartTag()���ã�����Ƿ�
 */
public class OrderInfoTag extends BodyTagSupport {

	private static final long serialVersionUID = 3295700202417719334L;

	// ��������
	private int num = 0;
	// ���β�Ʒ����
	private Trip trip = null;
	// ����ʱ��
	private Date time = null;

	// ----��ǩ��ִ�������ô˷���----
	public int doAfterBody() {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div class='order_box border_3 position_r'>");
		sb.append("		<h1 class='order_name'>");
		sb.append("			<span>" + trip.getTitle() + "</span>");
		sb.append("		</h1>");
		sb.append("		<dl class='order_line'>");
		sb.append("			<dt>������</dt>");
		sb.append("			<dd>");
		sb.append("				<table class='pro_table'>");
		sb.append("					<tbody>");
		sb.append("						<tr>");
		sb.append("							<th width='70%' style='text-align: left'>��Ʒ��Ϣ</th>");
		sb.append("							<th width='20%' style='text-align: left'>����ʱ��</th>");
		sb.append("							<th width='10%'>����</th>");
		sb.append("						</tr>");
		sb.append("						<tr>");
		sb.append("							<td>" + trip.getSTitle() + "</td>");
		sb.append("							<td>" + new SimpleDateFormat("yyyy-MM-dd").format(time) + "</td>");
		sb.append("							<td style='text-align: center'>" + num + "</td>");
		sb.append("						</tr>");
		sb.append("					</tbody>");
		sb.append("				</table>");
		sb.append("			</dd>");
		sb.append("		</dl>");
		sb.append("		<div class='clear'></div>");
		sb.append("	</div>");

		try {
			JspWriter out = pageContext.getOut();
			out.print(sb.toString().trim());
		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;
	}

	// ----��ǩ����ʱ���ô˷���-------
	public int doEndTag() {
		try {
			// ----��ǰ���ǩ����������ҳ��-------
			bodyContent.writeOut(bodyContent.getEnclosingWriter());
		} catch (Exception e) {
			System.out.println(e);
		}
		return EVAL_PAGE;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getCountNum() {
		return num;
	}

	public void setCountNum(int countNum) {
		this.num = countNum;
	}
}
