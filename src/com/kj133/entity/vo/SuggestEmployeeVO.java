package com.kj133.entity.vo;

public class SuggestEmployeeVO {
     private String stafferid;//员工号
     private String username;//姓名
     private String cardid; //卡号
     private String cardreaderid;//分站号
     private String shortname; //分站简称
     private String locatorid;//定位器号
     private String lshortname;//定位器名简称
     private String worktype;//工种
     private String cardname;
     private String cardtypename;
     
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getLocatorid() {
		return locatorid;
	}
	public void setLocatorid(String locatorid) {
		this.locatorid = locatorid;
	}
	public String getLshortname() {
		return lshortname;
	}
	public void setLshortname(String lshortname) {
		this.lshortname = lshortname;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardtypename() {
		return cardtypename;
	}
	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}
}
