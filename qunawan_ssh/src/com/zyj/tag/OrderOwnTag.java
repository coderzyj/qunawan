package com.zyj.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * ������ȷ����Ϣ��ʾ��ǩ ͨ���Զ��嶯̬��ǩʵ�� JSP1.Xʵ�ֵ���TagSupport���е�doEndTag()����
 * ��JSP2.Xֻʵ��SimpleTag�ӿ���Ψһ��doTag()���������Ҹ÷���û�з���ֵ
 * 
 * @author milo
 *
 */
public class OrderOwnTag extends SimpleTagSupport implements DynamicAttributes {

	// �������ݵ���ʾ����
	ArrayList<String> keys = new ArrayList<String>();
	// �������ݵ���ʾֵ
	ArrayList<Object> values = new ArrayList<Object>();

	/**
	 * �������ֵ
	 */
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		// �������ϣ������
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			Object value = values.get(i);
			// ���������Ӧ��keyֵ��valueֵ
			out.println("<dl class='user_dl'><dt><span class='red'>*</span>" + key
					+ "</dt><dd class='error_show'>"
					+ "<span class='su_info'><label>" + value + "</label></span>"
					+ "<span class='error_text'><i class='tip-icon tip-icon-error'></i><label></label></span></dd></dl>");
		}
	}

	/**
	 * ���ö�̬����
	 * localName ���ձ�ǩ�еȺ���ߵ�����
	 * value ���յȺ��ұߵ�����
	 */
	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		// �жϵ�ǰ���յ��������Ƿ�������
		if ("real_name".equals(localName)) {
			keys.add("������");
		}
		// �жϵ�ǰ���յ��������Ƿ��ǵ绰
		else if ("phone".equals(localName)) {
			keys.add("�ֻ����룺");
		}
		// �жϵ�ǰ���յ��������Ƿ��������ַ
		else if ("email".equals(localName)) {
			keys.add("�����ַ��");
		}
		// ���յ���valueֵ���ַ��ж�
		if (value == null || "".equals(value)) {
			values.add("δ����");
		} else {
			values.add(value);
		}
	}

}
