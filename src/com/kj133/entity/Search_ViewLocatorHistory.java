package com.kj133.entity;

 

public class Search_ViewLocatorHistory {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 定位器历史查询
	 * */
    private String lid;
    private String lname;
    private String stime;
    private String etime;
    private String minstime;
    private String maxstime;
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
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}
