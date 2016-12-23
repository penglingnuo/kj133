package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Onscene extends BaseObject{

	/**
	 * 在场信息查询
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
	private String operator;
	private String stime;
	private String etime;
	private String cardreaderid;
	private String crname;
	private String onscenecount;
	private String mapid;
	private String mapname;
	
	 
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getCrname() {
		return crname;
	}
	public void setCrname(String crname) {
		this.crname = crname;
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
	public String getMapname() {
		return mapname;
	}
	public void setMapname(String mapname) {
		this.mapname = mapname;
	}
	public String getOnscenecount() {
		return onscenecount;
	}
	public void setOnscenecount(String onscenecount) {
		this.onscenecount = onscenecount;
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
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}

}
