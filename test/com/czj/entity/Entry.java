package com.czj.entity;

import java.util.List;

public class Entry {
	
	private String entry_id = "";
	
	private String account_code = "";
	
	private String remark = "";
	
	private String settlement = "";
	
	private String document_id = "";
	
	private String document_date = "";
	
	private String currency = "";
	
	private Double unit_price = 0.00000000;
	
	private Double exchange_rate1 = 0.00000000;
	
	private Double exchange_rate2 = 1.00000000;
	
	private Double debit_quantity = 0.00000000;
	
	private Double primary_debit_amount = 0.00000000;
	
	private Double secondary_debit_amount = 0.00000000;
	
	private Double natural_debit_currency = 0.00000000;
	
	private Double credit_quantity = 0.00000000;
	
	private Double primary_credit_amount = 0.00000000;
	
	private Double secondary_credit_amount = 0.00000000;
	
	private Double natural_credit_currency = 0.00000000;
	
	private String bill_type = "";
	
	private String bill_id = "";
	
	private String bill_date = "";
	
	private List<Item> itemList;
	
	public String getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}

	public String getAccount_code() {
		return account_code;
	}

	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getDocument_id() {
		return document_id;
	}

	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}

	public String getDocument_date() {
		return document_date;
	}

	public void setDocument_date(String document_date) {
		this.document_date = document_date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getExchange_rate1() {
		return exchange_rate1;
	}

	public void setExchange_rate1(Double exchange_rate1) {
		this.exchange_rate1 = exchange_rate1;
	}

	public Double getExchange_rate2() {
		return exchange_rate2;
	}

	public void setExchange_rate2(Double exchange_rate2) {
		this.exchange_rate2 = exchange_rate2;
	}

	public Double getDebit_quantity() {
		return debit_quantity;
	}

	public void setDebit_quantity(Double debit_quantity) {
		this.debit_quantity = debit_quantity;
	}

	public Double getPrimary_debit_amount() {
		return primary_debit_amount;
	}

	public void setPrimary_debit_amount(Double primary_debit_amount) {
		this.primary_debit_amount = primary_debit_amount;
	}

	public Double getSecondary_debit_amount() {
		return secondary_debit_amount;
	}

	public void setSecondary_debit_amount(Double secondary_debit_amount) {
		this.secondary_debit_amount = secondary_debit_amount;
	}

	public Double getNatural_debit_currency() {
		return natural_debit_currency;
	}

	public void setNatural_debit_currency(Double natural_debit_currency) {
		this.natural_debit_currency = natural_debit_currency;
	}

	public Double getCredit_quantity() {
		return credit_quantity;
	}

	public void setCredit_quantity(Double credit_quantity) {
		this.credit_quantity = credit_quantity;
	}

	public Double getPrimary_credit_amount() {
		return primary_credit_amount;
	}

	public void setPrimary_credit_amount(Double primary_credit_amount) {
		this.primary_credit_amount = primary_credit_amount;
	}

	public Double getSecondary_credit_amount() {
		return secondary_credit_amount;
	}

	public void setSecondary_credit_amount(Double secondary_credit_amount) {
		this.secondary_credit_amount = secondary_credit_amount;
	}

	public Double getNatural_credit_currency() {
		return natural_credit_currency;
	}

	public void setNatural_credit_currency(Double natural_credit_currency) {
		this.natural_credit_currency = natural_credit_currency;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

}
