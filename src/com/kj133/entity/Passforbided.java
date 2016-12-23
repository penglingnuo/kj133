package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Passforbided extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  recordid;
	private String  caption;
	private String  cardid;
	private String  cardreaderid;
	private String  pass;
	private String  passmode;
	private String  starttime;
	private String  endtime;
	private String  operationnumber;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
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
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getOperationnumber() {
		return operationnumber;
	}
	public void setOperationnumber(String operationnumber) {
		this.operationnumber = operationnumber;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPassmode() {
		return passmode;
	}
	public void setPassmode(String passmode) {
		this.passmode = passmode;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
}
