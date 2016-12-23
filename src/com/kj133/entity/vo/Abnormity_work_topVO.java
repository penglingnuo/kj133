package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Abnormity_work_topVO extends Staffer {

	/**
	 * 工作异常员工清单
	 */
	private static final long serialVersionUID = 1L;

	private String stafferid; // 员工编号

	private String cardid; // 卡号

	private String name; // 姓名

	private String occupation; // 职务

	private int abnormitylist; // 异常次数

	private String uptime; // 升井时间

	private String downtime; // 入井时间

	private String gro; // 班组

	private String worktype; // 工种

	public int getAbnormitylist() {
		return abnormitylist;
	}

	public void setAbnormitylist(int abnormitylist) {
		this.abnormitylist = abnormitylist;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getGro() {
		return gro;
	}

	public void setGro(String gro) {
		this.gro = gro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

}
