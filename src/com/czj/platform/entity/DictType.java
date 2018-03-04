package com.czj.platform.entity;

import java.io.Serializable;

/**
 * 数据字典表
 * @author Chu,zhujun
 * @version 2016年1月18日 下午2:39:14
 */
public class DictType extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -6750660919518153021L;

	//数据字典code
	private String code;
	
	//数据字典名称，展示在前台
	private String name;
	
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

}
