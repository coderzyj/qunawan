package com.zyj.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * �����࣬�ṩһЩ��̬�Ĺ��ܺ���
 */
@SuppressWarnings("unchecked")
public class Utils {
	
	public static int getPageNum(String pageStr) {
		if ("".equals(pageStr) || pageStr == null) {
			pageStr = "1";
		}
		// ��ǰҳ��
		return Integer.parseInt(pageStr);
	}

	/**
	 * ��iso8859-1������ַ�����Ϊutf-8��ʽ�������������
	 * 
	 * @param s
	 *            iso8859-1������ַ���
	 * @return utf-8������ַ���
	 */
	public static String isoToUtf(String s) {
		try {
			return new String(s.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �ж����temp�Ƿ����������ʽ xxx@xxx.xxx
	 * @param temp
	 * @return
	 */
	public static boolean isEmail(String temp) {
		String rule = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(rule);
		Matcher matcher = regex.matcher(temp);
		return matcher.matches();
	}
	
	/**
	 * ���ַ�������MD5���ܣ� ��Ҫ�������벿��
	 */
	public static String toMD5(String data) {
		if (data == null)
			return null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			// ����ת��
			md.update(data.getBytes());
			String result = new BigInteger(1, md.digest()).toString(16);

			return result;

		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}


	/**
	 * ����request���� ��ȡ�ϴ���ͼƬ�ļ�
	 */
	public static byte[] analysisForm(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = null;
		byte[] image = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // ����request����
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) { // ����Ǳ��� �����Ƿ��ļ��ϴ�Ԫ��
				continue;
			} else {
				String fieldName = item.getFieldName(); // �ļ�����name���Ե�ֵ
				if ("fileList".equals(fieldName)) {
					try {
						BufferedImage bi = ImageIO.read(item.getInputStream());
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(bi, "png", baos);
						image = baos.toByteArray();
						baos.close();
						System.out.println("�����ϴ������ļ���" + image);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return image;
	}

	/**
	 * ͨ��BufferedImage��ȡͼƬ��Blob����
	 * @param image ͼƬ
	 * @return Blob ����
	 */
	public static final byte[] getDataFromImage(BufferedImage image) throws IOException {
		/** ʵѵ����006���û�ͷ���ϴ� - ��BufferedImage����ת��Ϊbyte���顾START�� */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", baos);
		baos.flush();
		baos.close();
		return baos.toByteArray();
		/** ʵѵ����006���û�ͷ���ϴ� - ��BufferedImage����ת��Ϊbyte���顾END�� */
	}

	/**
	 * ���ַ���ת����java.sql.Date����
	 * 
	 * @param ymd
	 *            ��ȡ���ı��ַ���
	 * @return ����sql��Date
	 */
	public static final Date stringToDate(String ymd) {
		java.sql.Date sqlDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.util.Date date = sdf.parse(ymd);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	/**
	 * ����byte���飬�����ļ�
	 */
	public static void getFile(byte[] bfile, String filePath) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// �ж��ļ�Ŀ¼�Ƿ����
				dir.mkdirs();
			}
			file = new File(filePath);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * �������ͼƬ�ļ������߱��
	 * 
	 * @return
	 */
	public static String createName() {
		java.text.DateFormat format = new java.text.SimpleDateFormat("hhmmss");
		return format.format(new java.util.Date()) + (int) (Math.random() * 89 + 10);
	}

	public static String dateToString(java.util.Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}
