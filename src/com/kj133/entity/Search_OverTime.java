package com.kj133.entity;



public class Search_OverTime  {
  
 
/**
   * 下井超时
   * */
	private String stime;
	private String etime;
	private String sid;
	private String gro;
	private String dpt;
	private String minstime;
	private String maxstime;
	private String minetime;
	private String maxetime;
	private String hours;
	private String userid;
	private String cdn;//查询条件
	
	
	public String getCdn() {
		return cdn;
	}
	public void setCdn(String cdn) {
		this.cdn = cdn;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
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
	public String getMaxetime() {
		return maxetime;
	}
	public void setMaxetime(String maxetime) {
		this.maxetime = maxetime;
	}
	public String getMaxstime() {
		return maxstime;
	}
	public void setMaxstime(String maxstime) {
		this.maxstime = maxstime;
	}
	public String getMinetime() {
		return minetime;
	}
	public void setMinetime(String minetime) {
		this.minetime = minetime;
	}
	public String getMinstime() {
		return minstime;
	}
	public void setMinstime(String minstime) {
		this.minstime = minstime;
	}
}
