package com.zyj.service;

import java.util.List;

import com.zyj.entity.Theme;

public interface ThemeService {
	List<Theme> getThemeByName(String name);
	List<String> getPageThemesByType(int id, int start, int maxCount);
	Theme getThemeById(int id);
}
