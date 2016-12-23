package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Manamount_queryVO extends Staffer {

	/**
	 * 人数曲线查询
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String areaID;

	private String recordtime;
	private String visitorcount;
	private String minercount;
	private String peoplecount;
	private String maxsum;
	private String caijuesum;
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
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
	 
	 
	public String getMinercount() {
		return minercount;
	}
	public void setMinercount(String minercount) {
		this.minercount = minercount;
	}
	public String getPeoplecount() {
		return peoplecount;
	}
	public void setPeoplecount(String peoplecount) {
		this.peoplecount = peoplecount;
	}
	public String getVisitorcount() {
		return visitorcount;
	}
	public void setVisitorcount(String visitorcount) {
		this.visitorcount = visitorcount;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	 
	



}
