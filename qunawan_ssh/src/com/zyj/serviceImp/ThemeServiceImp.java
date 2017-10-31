package com.zyj.serviceImp;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.zyj.dao.ThemeDao;
import com.zyj.entity.Theme;
import com.zyj.service.ThemeService;
@Service
@Transactional
public class ThemeServiceImp implements ThemeService{
	private ThemeDao dao;
	@Resource(name="themeDaoImp")
	public void setDao(ThemeDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Theme> getThemeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPageThemesByType(int id, int start, int maxCount) {
		// TODO Auto-generated method stub
		return dao.getPageThemesByType(id, start, maxCount);
	}

	@Override
	public Theme getThemeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
