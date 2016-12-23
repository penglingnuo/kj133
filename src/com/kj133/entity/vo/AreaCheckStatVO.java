package com.kj133.entity.vo;



public class AreaCheckStatVO{
	
	/**
	 * 区域考勤次数统计
	 */
	private static final long serialVersionUID = 1L;
	 private String areaid;
	 private String areaname;
	 private String cardid;
	 private String stafferid;
	 private String name;
	 private String gro;
	 private String worktype;
	 private String worktime;
	 private String sticktime;
	 private String areacount;
	public String getAreacount() {
		return areacount;
	}
	public void setAreacount(String areacount) {
		this.areacount = areacount;
	}
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
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getSticktime() {
		return sticktime;
	}
	public void setSticktime(String sticktime) {
		this.sticktime = sticktime;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	 



}
