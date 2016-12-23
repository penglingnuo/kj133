package com.kj133.entity;

 

public class Search_Locator_Batteries  {
 
 
/**
    * 定位器电池报警
    * */
	
	private String stime;
	private String etime;
	private String lid;
	private String minstime;
	private String maxstime;
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
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getMaxstime() {
		return maxstime;
	}
	public void setMaxstime(String maxstime) {
		this.maxstime = maxstime;
	}
	public String getMinstime() {
		return minstime;
	}
	public void setMinstime(String minstime) {
		this.minstime = minstime;
	}
}
