package com.zyj.entity;

public class Files {
	private int id;
	private String title;
	private String name;
	private String contentType;
	private String size;
	private String filePath;
	public Files(){}
	
	public Files(String title, String name, String contentType,
			String size, String filePath) {
		super();

		this.title = title;
		this.name = name;
		this.contentType = contentType;
		this.size = size;
		this.filePath = filePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
