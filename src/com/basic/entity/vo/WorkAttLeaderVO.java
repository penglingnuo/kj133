package com.basic.entity.vo;

public class WorkAttLeaderVO {

	private String name;// 姓名
	private String cardid;// 卡号
	private String group;// 班组
	private String occupation;// 职务
	private String downtime;// 入井时间
	private String worktime;//工作时长
	private String crname;// 分站名称
	private String starttime;// 进入分站时间
	private String overstate;
	private String state;
	private int total;//井下人数统计
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
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
	public String getCrname() {
		return crname;
	}
	public void setCrname(String crname) {
		this.crname = crname;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getOverstate() {
		return overstate;
	}
	public void setOverstate(String overstate) {
		this.overstate = overstate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
