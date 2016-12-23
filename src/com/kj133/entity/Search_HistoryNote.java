package com.kj133.entity;



public class Search_HistoryNote  {
  
 
	/**
   *历史记录查询
   * */
  
	private String stime;
	private String info[];
	private String etime;
	private String cid;
	private  String rad;
	private String minstime;
	private String maxstime;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	public String[] getInfo() {
		return info;
	}
	public void setInfo(String[] info) {
		this.info = info;
	}
 
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
 
   public String getRad() {
		return rad;
	}
	public void setRad(String rad) {
		this.rad = rad;
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
