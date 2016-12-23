package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class On_Load_ParticularVO  extends Staffer{

	/**
	 * 在场信息查询里的详细信息
	 */
	private static final long serialVersionUID = 1L;
   
	private String sid;
	private String name;
	private String stime;
	private String etime;
	private String cardreaderid;
	private String cid;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}
