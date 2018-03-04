package com.czj.platform.entity;

import java.util.Date;

public class User extends BaseEntity {
	private static final long serialVersionUID = -1L;

	private String username;
	
	private String password;
	
	private Integer is_invoked;
	
	private String remark;
	
	private Date create_time;
	
	private Date modify_time;
	
	private String creator;
	
	private String modifier;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIs_invoked() {
		return is_invoked;
	}

	public void setIs_invoked(Integer is_invoked) {
		this.is_invoked = is_invoked;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
