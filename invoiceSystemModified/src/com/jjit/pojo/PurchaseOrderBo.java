package com.jjit.pojo;

import java.util.HashMap;

public class PurchaseOrderBo {
	private String uuid;
	private String duration;
	private String rate;
	private String particular;
	private String paymentterms;
	private String purchaseorderno;
	private String stextra;
	private String tax;
	private String total;
	private String actotal;
	private String pototal;
	private HashMap<Integer,ExpenseBo> expenses = new HashMap<Integer,ExpenseBo>();
	public HashMap<Integer,ExpenseBo> getExpenses() {
		return expenses;
	}

	public void setExpenses(HashMap<Integer,ExpenseBo> expenses) {
		this.expenses = expenses;
	}
		public String getPototal() {
		return pototal;
	}

	public void setPototal(String pototal) {
		this.pototal = pototal;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getActotal() {
		return actotal;
	}

	public void setActotal(String actotal) {
		this.actotal = actotal;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getParticular() {
		return particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getPaymentterms() {
		return paymentterms;
	}

	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}

	public String getPurchaseorderno() {
		return purchaseorderno;
	}

	public void setPurchaseorderno(String purchaseorderno) {
		this.purchaseorderno = purchaseorderno;
	}

	public String getStextra() {
		return stextra;
	}

	public void setStextra(String stextra) {
		this.stextra = stextra;
	}

}
