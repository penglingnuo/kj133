package com.kj133.entity;

 

public class Search_ViewReaderHistory  {
 
	/**
	  * 分站历史查询
	  * */
	private String history_cid;
	private String history_cname;
	private String history_stime;
	private String history_etime;
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
	public String getHistory_cid() {
		return history_cid;
	}
	public void setHistory_cid(String history_cid) {
		this.history_cid = history_cid;
	}
	public String getHistory_cname() {
		return history_cname;
	}
	public void setHistory_cname(String history_cname) {
		this.history_cname = history_cname;
	}
	public String getHistory_etime() {
		return history_etime;
	}
	public void setHistory_etime(String history_etime) {
		this.history_etime = history_etime;
	}
	public String getHistory_stime() {
		return history_stime;
	}
	public void setHistory_stime(String history_stime) {
		this.history_stime = history_stime;
	}
 
}
