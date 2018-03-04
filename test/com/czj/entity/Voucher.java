package com.czj.entity;

public class Voucher {
	
	private String id = "";
	
	private VoucherHead voucherHead;
	
	private VoucherBody voucherBody;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VoucherHead getVoucherHead() {
		return voucherHead;
	}

	public void setVoucherHead(VoucherHead voucherHead) {
		this.voucherHead = voucherHead;
	}

	public VoucherBody getVoucherBody() {
		return voucherBody;
	}

	public void setVoucherBody(VoucherBody voucherBody) {
		this.voucherBody = voucherBody;
	}

}
