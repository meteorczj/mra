package com.czj.entity;

public class Ufinterface {
	
	private String roottag = "";
	
	private String billtype = "";
	
	private String replace = "";
	
	private String receiver = "";
	
	private String sender = "";
	
	private String isexchange = "";
	
	private String filename = "";
	
	private String proc = "";
	
	private String operation = "";
	
	private Voucher voucher;

	public String getRoottag() {
		return roottag;
	}

	public void setRoottag(String roottag) {
		this.roottag = roottag;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getIsexchange() {
		return isexchange;
	}

	public void setIsexchange(String isexchange) {
		this.isexchange = isexchange;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getProc() {
		return proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

}
