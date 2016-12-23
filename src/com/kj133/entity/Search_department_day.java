package com.kj133.entity;

public class Search_department_day {
  /**
   * 部门时段查询
   * */

	 private String stime;
	 private String etime;
	 private String sid;
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
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
