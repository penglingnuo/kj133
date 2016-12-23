package com.kj133.entity;

public class Search_Pworkset {
	
	 /**
	  *  人员工作设置
	  */
	 private String sid;  //员工号
	 private String em;  //员工
	 private String worktype; //工种
	 private String mintime;//最小工作时间
	 private String zuhe;//地点设置：ID：名称
	 private String appointminute;//指定时间
	 private String bantype;//班类型
	 private String maxtime;//指定时间
	public String getBantype() {
		return bantype;
	}
	public void setBantype(String bantype) {
		this.bantype = bantype;
	}
	public String getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}
	public String getAppointminute() {
		return appointminute;
	}
	public void setAppointminute(String appointminute) {
		this.appointminute = appointminute;
	}
	public String getZuhe() {
		return zuhe;
	}
	public void setZuhe(String zuhe) {
		this.zuhe = zuhe;
	}
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
	public String getEm() {
		return em;
	}
	public void setEm(String em) {
		this.em = em;
	}

}
