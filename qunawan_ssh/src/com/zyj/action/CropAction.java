package com.zyj.action;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import com.zyj.entity.Files;
import com.zyj.entity.User;
import com.zyj.globle.Constants;
import com.zyj.globle.Globle;
import com.zyj.service.UserService;
import com.zyj.utils.Utils;

@Controller("cropaction")
@Scope("prototype")
public class CropAction {
	HttpServletRequest request;
	UserService us;
	Files file=new Files();
	private static final String HEAD_PIC = "headPic";
	private static final String COMMENTPIC = "commentPic";
	File upLoad;
	private String upLoadContentType;
	private String upLoadFileName;

	@Resource(name="userServiceImp")
	public void setUs(UserService us) {
		this.us = us;
	}
	
	public File getUpLoad() {
		return upLoad;
	}

	public void setUpLoad(File upLoad) {
		this.upLoad = upLoad;
	}

	public String getUpLoadContentType() {
		return upLoadContentType;
	}

	public void setUpLoadContentType(String upLoadContentType) {
		this.upLoadContentType = upLoadContentType;
	}

	public String getUpLoadFileName() {
		return upLoadFileName;
	}

	public void setUpLoadFileName(String upLoadFileName) {
		this.upLoadFileName = upLoadFileName;
	}
	
	public void setFile(Files file) {
		this.file = file;
	}

	public Files getFile() {
		return file;
	}

	public String upLoad()
	{
		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		String uname=user.getName();
		String realpath =ServletActionContext.getServletContext().getRealPath("/");
		System.out.println("真实路径："+realpath);
		if(upLoad==null)
			System.out.println("文件为空");
		if(upLoad!=null)
		{
			File savefile = new File(new File(realpath), upLoadFileName);
			System.out.println("文件名："+upLoadFileName);
			if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
			try {
				FileUtils.copyFile(upLoad, savefile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setImgPath(upLoadFileName);
	        
		}
		us.Update(user);
			return "success";

	}




	
}
