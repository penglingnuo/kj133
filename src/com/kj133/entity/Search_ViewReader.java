package com.kj133.entity;

 

public class Search_ViewReader {
 
	/**
	 * 编辑分站
	 * */
	private String cid;
	private String cname;
	private String stime;
	private String etime;
	private String minstime;
    private String maxstime;
    private String checkreader;//用于分站状态，是否使用
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getCheckreader() {
		return checkreader;
	}
	public void setCheckreader(String checkreader) {
		this.checkreader = checkreader;
	}
}
