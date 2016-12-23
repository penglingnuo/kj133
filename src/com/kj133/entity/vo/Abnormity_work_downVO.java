package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Abnormity_work_downVO extends Staffer {

	/**
	 * 员工工作异常明细
	 */
	private static final long serialVersionUID = 1L;

	private String cardid; // 卡号

	private String stafferid; // 员工编号

	private String locus; // 要求地点

	private String name; // 姓名

	private String requiretime; // 要求到达时间

	private String reachtime; // 实际到达时间

	private String occupation; // 职务

	private String uptime; // 升井时间

	private String downtime; // 入井时间

	private String gro; // 班组

	private String worktype; // 工种

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

	public String getLocus() {
		return locus;
	}

	public void setLocus(String locus) {
		this.locus = locus;
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

	public String getReachtime() {
		return reachtime;
	}

	public void setReachtime(String reachtime) {
		this.reachtime = reachtime;
	}

	public String getRequiretime() {
		return requiretime;
	}

	public void setRequiretime(String requiretime) {
		this.requiretime = requiretime;
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

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

}
