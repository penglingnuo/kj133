package com.kj133.entity;

 

public class Search_Employee_check_daily {
 
/**
 * 员工考勤日报
 * */
	 private String stime;
	 private String gro;
	 private String cardid;
	 private String type;
	 
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
