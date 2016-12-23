package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Onscenedata extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
    private String operator;
	private String cardid;
	private String cardreaderid;
	private String stime;
	private String etime;
	private String mapid;
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
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}

}
