package com.czj.platform.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6330873725841352753L;

	private String id;

	private Map<String, Object> map = new HashMap<String, Object>();
	
	//附加
	private int page;//当前的页数
	
	private int rows;//每页显示的数目
	
	private int start;//分页的开始数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
