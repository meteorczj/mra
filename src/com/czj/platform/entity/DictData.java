package com.czj.platform.entity;

import java.io.Serializable;

/**
 * 数据字典分类表
 * @author Chu,zhujun
 * @version 2016年1月18日 下午2:37:29
 */
public class DictData extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -6750660919518153021L;

	//数据字典分类code
	private String code;
	
	//数据字典分类名称，展示在前台
	private String name;
	
	//数据字典分类ID
	private String group_id;
	
	//数据字典分类code
	private String group_code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	
}
