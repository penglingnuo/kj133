package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class WorkattendanceEx extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recordid;

	private String cardid;

	private String stafferid;

	private String downtime;

	private String uptime;

	private String downcardreaderid;

	private String downlocatorid;

	private String uplocatorid;

	private String upcardreaderid;

	private String ifadd;

	private String kqtime;

	private String iridesNodeid;

	private String ifupdate;

	private String cardstate;

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardstate() {
		return cardstate;
	}

	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}

	public String getDowncardreaderid() {
		return downcardreaderid;
	}

	public void setDowncardreaderid(String downcardreaderid) {
		this.downcardreaderid = downcardreaderid;
	}

	public String getDownlocatorid() {
		return downlocatorid;
	}

	public void setDownlocatorid(String downlocatorid) {
		this.downlocatorid = downlocatorid;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getIfadd() {
		return ifadd;
	}

	public void setIfadd(String ifadd) {
		this.ifadd = ifadd;
	}

	public String getIfupdate() {
		return ifupdate;
	}

	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}

	public String getIridesNodeid() {
		return iridesNodeid;
	}

	public void setIridesNodeid(String iridesNodeid) {
		this.iridesNodeid = iridesNodeid;
	}

	public String getKqtime() {
		return kqtime;
	}

	public void setKqtime(String kqtime) {
		this.kqtime = kqtime;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public String getUpcardreaderid() {
		return upcardreaderid;
	}

	public void setUpcardreaderid(String upcardreaderid) {
		this.upcardreaderid = upcardreaderid;
	}

	public String getUplocatorid() {
		return uplocatorid;
	}

	public void setUplocatorid(String uplocatorid) {
		this.uplocatorid = uplocatorid;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

}
