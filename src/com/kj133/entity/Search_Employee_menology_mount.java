package com.kj133.entity;

public class Search_Employee_menology_mount {
	
	 /**
	  *  员工考勤设置
	  */
	 private String sid;  //员工号
	 private String worktype; //工种
	 private String mintime;//最小工作时间
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getMintime() {
		return mintime;
	}
	public void setMintime(String mintime) {
		this.mintime = mintime;
	}

}
