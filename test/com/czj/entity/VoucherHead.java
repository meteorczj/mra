package com.czj.entity;

public class VoucherHead {
	
	private String company = "";//公司主键 ，不能为空，需要参照基础数据(公司目录)
	
	private String voucher_type = "";//凭证类别，不能为空，需要参照基础数据(凭证类别)
	
	private String fiscal_year = "";//会计年度，不能为空
	
	private String accounting_period = "";//会计期间，不能为空
	
	private String voucher_id = "";//凭证号，不能为空
	
	private String attachment_number = "";//附单据数，不能为空
	
	private String prepareddate = "";//制单日期，不能为空
	
	private String enter = "";//制单人，不能为空
	
	private String cashier = "";//出纳，可以为空
	
	private String signature = "N";//是否已签字，不能为空，默认为N
	
	private String checker = "";//审核人，可以为空
	
	private String posting_date = "";//记账日期，可以为空
	
	private String posting_person = "";//记账人，可以为空
	
	private String voucher_making_system = "";//来源系统，不能为空
	
	private String memo1 = "";//备注，可以为空
	
	private String memo2 = "";//备注，可以为空
	
	private String reserve1 = "";//保留项，可以为空
	
	private String reserve2 = "";//保留项，可以为空
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getVoucher_type() {
		return voucher_type;
	}

	public void setVoucher_type(String voucher_type) {
		this.voucher_type = voucher_type;
	}

	public String getFiscal_year() {
		return fiscal_year;
	}

	public void setFiscal_year(String fiscal_year) {
		this.fiscal_year = fiscal_year;
	}

	public String getAccounting_period() {
		return accounting_period;
	}

	public void setAccounting_period(String accounting_period) {
		this.accounting_period = accounting_period;
	}

	public String getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(String voucher_id) {
		this.voucher_id = voucher_id;
	}

	public String getAttachment_number() {
		return attachment_number;
	}

	public void setAttachment_number(String attachment_number) {
		this.attachment_number = attachment_number;
	}

	public String getPrepareddate() {
		return prepareddate;
	}

	public void setPrepareddate(String prepareddate) {
		this.prepareddate = prepareddate;
	}

	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	public String getPosting_person() {
		return posting_person;
	}

	public void setPosting_person(String posting_person) {
		this.posting_person = posting_person;
	}

	public String getVoucher_making_system() {
		return voucher_making_system;
	}

	public void setVoucher_making_system(String voucher_making_system) {
		this.voucher_making_system = voucher_making_system;
	}

	public String getMemo1() {
		return memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

}
