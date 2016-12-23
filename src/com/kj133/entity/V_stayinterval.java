package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class V_stayinterval extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardid;
	private String cardreaderid;
	private String intime;
	private String inlocator;
	private String outtime;
	private String outlocator;
	private String outreason;
	private String reintime;
	private String stayinterval;
	private String outinterval;
	private String interrupttimes;
	private String stafferid;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getInlocator() {
		return inlocator;
	}
	public void setInlocator(String inlocator) {
		this.inlocator = inlocator;
	}
	public String getInterrupttimes() {
		return interrupttimes;
	}
	public void setInterrupttimes(String interrupttimes) {
		this.interrupttimes = interrupttimes;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getOutinterval() {
		return outinterval;
	}
	public void setOutinterval(String outinterval) {
		this.outinterval = outinterval;
	}
	public String getOutlocator() {
		return outlocator;
	}
	public void setOutlocator(String outlocator) {
		this.outlocator = outlocator;
	}
	public String getOutreason() {
		return outreason;
	}
	public void setOutreason(String outreason) {
		this.outreason = outreason;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	public String getReintime() {
		return reintime;
	}
	public void setReintime(String reintime) {
		this.reintime = reintime;
	}
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getStayinterval() {
		return stayinterval;
	}
	public void setStayinterval(String stayinterval) {
		this.stayinterval = stayinterval;
	}

}
