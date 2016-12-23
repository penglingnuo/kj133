package com.kj133.entity;

public class Search_DownWellCount {

	/**
	 * 查询下井次数统计 
	 * 及
	 * 未出勤人员明细
	 */
	private String stime;

	private String etime;

	private String group;

	private String worktype;

	private String stafferid;

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
}
