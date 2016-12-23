package com.kj133.entity;

/**
 * 人数曲线查询
 * 
 * @author wang
 * 
 */

public class Search_manamount_query {

	// private String sid;

	private String stime;
		
	private String etime;

	private String areaname;

	private String areatype;

	private String aid;
	private String parentarea;
	private String maxsum;
	private String caijuesum;
	private String areaorder;
	private String stayminute;
	
	// private String gro;
	// private String type;
	// private String banci;
	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	/*
	 * public String getGro() { return gro; }
	 * 
	 * public void setGro(String gro) { this.gro = gro; }
	 * 
	 * public String getSid() { return sid; }
	 * 
	 * public void setSid(String sid) { this.sid = sid; }
	 */
	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getAreatype() {
		return areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAreaorder() {
		return areaorder;
	}

	public void setAreaorder(String areaorder) {
		this.areaorder = areaorder;
	}

	public String getCaijuesum() {
		return caijuesum;
	}

	public void setCaijuesum(String caijuesum) {
		this.caijuesum = caijuesum;
	}

	public String getMaxsum() {
		return maxsum;
	}

	public void setMaxsum(String maxsum) {
		this.maxsum = maxsum;
	}

	public String getParentarea() {
		return parentarea;
	}

	public void setParentarea(String parentarea) {
		this.parentarea = parentarea;
	}

	public String getStayminute() {
		return stayminute;
	}

	public void setStayminute(String stayminute) {
		this.stayminute = stayminute;
	}

	/*
	 * public String getType() { return type; }
	 * 
	 * public void setType(String type) { this.type = type; }
	 * 
	 * public String getBanci() { return banci; }
	 * 
	 * public void setBanci(String banci) { this.banci = banci; }
	 */

}
