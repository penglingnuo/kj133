package com.basic.entity.vo;

public class PanerWorkAttAreaVO {

	/** ---------------行走轨迹明细----------------------------------- */
	private String name;
	private String group;
	private String occupation;
	private String downtime;
	private String worktime;
	private String starttime;// 进入分站时间
	private String endtime;// 离开分站时间
	private String stayinterval;// 分站停留时间
	private String crname;// 分站名称
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDowntime() {
		return downtime;
	}
	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStayinterval() {
		return stayinterval;
	}
	public void setStayinterval(String stayinterval) {
		this.stayinterval = stayinterval;
	}
	public String getCrname() {
		return crname;
	}
	public void setCrname(String crname) {
		this.crname = crname;
	}
	
	
}
