package com.czj.platform.entity;

import java.io.Serializable;
import java.util.List;


/**
 * 表示业务应用系统的一个功能模块
 * 
 * @author czj
 * @since 2013/06/27
 */
public class Module extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2625362853330033196L;
	
	private Integer index;
	
	private String name;
	
	private String pinyin_code;

	private String title;

	private String path;

	private String parent;
	
	private Integer depth;

	private Integer isGroup;

	private Integer isInvoked;

	private String url;

	private String remark;
	
	private List<Module> children;
	
	public Module() {

	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin_code() {
		return pinyin_code;
	}

	public void setPinyin_code(String pinyin_code) {
		this.pinyin_code = pinyin_code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}

	public Integer getIsInvoked() {
		return isInvoked;
	}

	public void setIsInvoked(Integer isInvoked) {
		this.isInvoked = isInvoked;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

}
