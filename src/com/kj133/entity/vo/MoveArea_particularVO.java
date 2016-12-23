package com.kj133.entity.vo;

import com.kj133.entity.Locator;

public class MoveArea_particularVO extends Locator{

	/**
	 * 活动区域里面的详细信息
	 */
	private static final long serialVersionUID = 1L;
	private String cardreaderid;
	private String lid;
	private String shortname;
	private String x;
	private String y;
	private String stime;
	private String etime;
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
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
     
}
