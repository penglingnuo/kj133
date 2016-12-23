package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Activerange extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String recordid;
    private String operator;
    private String cardid;
    private String cardname;
    private String cardreaderid;
    private String crname;
    private String appeartimes;
    private String staytime;
    private String mapid;
    private String mapname;
    private String stafferid;
	public String getAppeartimes() {
		return appeartimes;
	}
	public void setAppeartimes(String appeartimes) {
		this.appeartimes = appeartimes;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
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
	public String getStaytime() {
		return staytime;
	}
	public void setStaytime(String staytime) {
		this.staytime = staytime;
	}
}
