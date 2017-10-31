package com.zyj.globle;


/**
 * �����࣬��Ҫ���ڹ���һЩ��������
 */
public class Constants {

	/**
	 * session�е��û�id
	 */
	public static String USER_KEY = "user";
	/**
	 * session�е��û�pic
	 */
	public static String USER_PIC = "ispic";
	/**
	 * ��֤��
	 */
	public static String CHECK_NUMBER_NAME = "identify_code";
	/**
	 * ������ʾ��Ϣ
	 */
	public static String ERROR= "err_msg";

	/**
	 * ������Ϣ�쳣��ʾ
	 */
	public static String COMMENT_INFO = "comment_info";

	/**
	 * session������ҳ��Ϣʵ��
	 */
	public static String SEARCH_BEAN = "s_bean";
	
	/**
	 * session�������б�
	 */
	public static String SEARCH_LIST = "t_list";

	/**
	 * ��Ʒ������һҳ��ʾ����������
	 */
	public static int COMMENT_DETAIL_MAX= 5;

	/**
	 * ��½��ת�ɹ�������
	 */
	public static String OK_URL = "campsg.qunawan.utils.Constant.ok_url";

	/**
	 * ��½��תʧ�ܵ�����
	 */
	public static String ERR_URL = "campsg.qunawan.utils.Constant.err_url";

	/**
	 * ��ϵ���б�һҳ�������
	 */
	public static final int CONTACT_MAX = 10;

	/**
	 * �����ɹ���Ajax��Ӧ�ַ���
	 */
	public static final String OP_SUCCESS = "success";
	/**
	 * ����ʧ�ܵ�Ajax��Ӧ�ַ���
	 */
	public static final String OP_FAIL = "fail";

	/**
	 * ��ҳ���ڲ�ѯ�����г̵������ַ���
	 */
	public static String[] TRIP_TYPE = { "�Լ���", "������", "������" };

	/**
	 * ��ҳ������ʾ�г��б��Map
	 */
	public static String INDEX_TRIP_MAP = "itemMap";

	/**
	 * ��ҳ������ʾ�����б��Map
	 */
	public static String INDEX_PLACE_MAP = "placeMap";

	/**
	 * ��ҳ������ʾ�����б��Map
	 */
	public static String INDEX_THEME_MAP = "themeMap";

	// ���۵�ҳ�����
	public static int COMMENT_MAX = 3;

	// ������ҳ�����
	public static int ORDER_MAX = 4;

	// ��ǰ����״̬Ϊ������ı�ʶ��
	public static String WAIT_PAY_STATE = "0";
	// ��ǰ����״̬Ϊ�����۵ı�ʶ��
	public static String WAIT_COMMENT_ORDER_STATE = "3";
	// ��ǰ����״̬Ϊ����ɵı�ʶ��
	public static String COMMENTED_ORDER_STATE = "4";

	// sequence����������
	public static String ORDER_TYPE = "order_type";

	// ������ϵ�˱�ʶ��
	public static Integer EM_CONTACT = 0;
	// �����˱�ʶ��
	public static Integer PL_CONTACT = 1;

	// �������۳�ʼ��������ʶ��
	public static String COMMENT_INIT = "init";
	// ��ȡ�����۶���������ʶ��
	public static String COMMENT_GET_WAIT_COMMENTS = "getWaitComments";
	// ��ȡ�����ۼ��ϲ�����ʶ��
	public static String COMMENT_GET_FINISH_COMMENT = "getFinishComment";
	// �ύ���۲�����ʶ��
	public static String COMMENT_SUBMIT_COMMENT = "submitComment";
	/**
	 * ��ϵ���еĽ�����ϵ�ˣ�������һ��һ
	 */
	public static Integer CONTACT_FOR_URGENT = 0;
	/**
	 * ��ϵ���е������ˣ�������һ�Զ�
	 */
	public static Integer CONTACT_FOR_PLAY = 1;

	/**
	 * ������ҳ�ı�ʶ�ַ���
	 */
	public static String INDEX_PAGE = "index.jsp";

	/**
	 * ��������ҳ�ı�ʶ�ַ���
	 */
	public static String SEARCH_PAGE = "search.jsp";

	/**
	 * ������������ҳ�ı�ʶ�ַ���
	 */
	public static String TRIP_DETAIL_PAGE = "trip_detail.jsp";

	/**
	 * ������session�е��û���Ծʱ��ͳ��
	 */
	public static String ACCESS_RECORD_IN_SESSION = "accessList";
	
	/**
	 * ��������ҳ���Ƿ������ ���ޡ��ȡ� ��cookie��ʶ
	 */
	public static String IS_USEFULL_FLAG = "-1";
	
	
	/**
	 * ������ServletContext�е��û��б�
	 */
	public static String ONLINE_USERS = "campsg.qunawan.listener.onLineListener.Map";
	
}
