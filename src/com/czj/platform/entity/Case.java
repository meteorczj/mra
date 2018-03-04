package com.czj.platform.entity;

import java.util.Date;

public class Case extends BaseEntity {
	
	private static final long serialVersionUID = -972184300794226151L;

	private String hosp_num;//住院号
	
	private String name;//姓名
	
	private Integer sex;//性别
	
	private Integer age;//年龄
	
	private String address;//地址
	
	private Date inhosp_date;//入院日期
	
	private String thrombus_part;//血栓部位
	
	private String thrombus_type;//血栓类型（中心型/外周型）
	
	private Date sick_date;//发病时间
	
	private Integer hosp_days;//住院天数
	
	private Integer is_lung;//肺栓塞（是/否
	
	private Integer is_cta;//肺动脉CTA（是/否）
	
	private String thrombus_reason;//血栓原因
	
	private Double charge_money;//总费用
	
	private Date create_time;
	
	private String creator;
	
	private Date modify_time;
	
	private String modifier;

	public String getHosp_num() {
		return hosp_num;
	}

	public void setHosp_num(String hosp_num) {
		this.hosp_num = hosp_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getInhosp_date() {
		return inhosp_date;
	}

	public void setInhosp_date(Date inhosp_date) {
		this.inhosp_date = inhosp_date;
	}

	public String getThrombus_part() {
		return thrombus_part;
	}

	public void setThrombus_part(String thrombus_part) {
		this.thrombus_part = thrombus_part;
	}

	public String getThrombus_type() {
		return thrombus_type;
	}

	public void setThrombus_type(String thrombus_type) {
		this.thrombus_type = thrombus_type;
	}

	public Date getSick_date() {
		return sick_date;
	}

	public void setSick_date(Date sick_date) {
		this.sick_date = sick_date;
	}

	public Integer getHosp_days() {
		return hosp_days;
	}

	public void setHosp_days(Integer hosp_days) {
		this.hosp_days = hosp_days;
	}

	public Integer getIs_lung() {
		return is_lung;
	}

	public void setIs_lung(Integer is_lung) {
		this.is_lung = is_lung;
	}

	public Integer getIs_cta() {
		return is_cta;
	}

	public void setIs_cta(Integer is_cta) {
		this.is_cta = is_cta;
	}

	public String getThrombus_reason() {
		return thrombus_reason;
	}

	public void setThrombus_reason(String thrombus_reason) {
		this.thrombus_reason = thrombus_reason;
	}

	public Double getCharge_money() {
		return charge_money;
	}

	public void setCharge_money(Double charge_money) {
		this.charge_money = charge_money;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
