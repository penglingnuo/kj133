package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class V_readerdata  extends BaseObject{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private String cardreaderid;
	private String recordtime;
	private String state;
	private String temperature;
	private String cardcount;
	private String interruptcount;
	private String ignorecount;
	public String getCardcount() {
		return cardcount;
	}
	public void setCardcount(String cardcount) {
		this.cardcount = cardcount;
	}
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getIgnorecount() {
		return ignorecount;
	}
	public void setIgnorecount(String ignorecount) {
		this.ignorecount = ignorecount;
	}
	public String getInterruptcount() {
		return interruptcount;
	}
	public void setInterruptcount(String interruptcount) {
		this.interruptcount = interruptcount;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
