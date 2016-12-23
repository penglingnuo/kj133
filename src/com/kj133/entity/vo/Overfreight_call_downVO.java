package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Overfreight_call_downVO extends Staffer {

	/**
	 * 区域超员时段小计
	 */
	private static final long serialVersionUID = 1L;
	
	private String areaid;
	private String aname;
	private String recordtime;
	private String maxsum;
	private String caijuesum;
	private String peoplecount;
	private String minercount;
	private String allchaomans;
	private String chaomans;
	public String getAllchaomans() {
		return allchaomans;
	}
	public void setAllchaomans(String allchaomans) {
		this.allchaomans = allchaomans;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getCaijuesum() {
		return caijuesum;
	}
	public void setCaijuesum(String caijuesum) {
		this.caijuesum = caijuesum;
	}
	public String getChaomans() {
		return chaomans;
	}
	public void setChaomans(String chaomans) {
		this.chaomans = chaomans;
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
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	


}
