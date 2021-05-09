package com.kh.ajax.homework.model.vo;

import java.sql.Date;

public class Phone {
	private String pname;
	private int amount;
	private Date pdate;
	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Phone(String pname, int amount, Date pdate) {
		super();
		this.pname = pname;
		this.amount = amount;
		this.pdate = pdate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	@Override
	public String toString() {
		return "Phone [pname=" + pname + ", amount=" + amount + ", pdate=" + pdate + "]";
	}
	
	
}
