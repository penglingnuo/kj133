package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Worktype_areadis_downVO extends Staffer {

	/**
	 * 工种区域分布表---人员明细
	 */
	private static final long serialVersionUID = 1L;
	
	private String areaid;
	private String areaname;
	private String cardid;
	private String stafferid;
	private String name;
	private String worktype;
	private String gro;
	private String starttime;
	private String inway;
	private String endtime;
	private String outway;
	private String downtime;
	private String uptime;
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
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
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getInway() {
		return inway;
	}
	public void setInway(String inway) {
		this.inway = inway;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOutway() {
		return outway;
	}
	public void setOutway(String outway) {
		this.outway = outway;
	}
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
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
