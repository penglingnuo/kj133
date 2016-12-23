package com.kj133.entity;

import org.speedframework.entity.BaseObject;


public class Checkattendance extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
	private String cardid;
	private String stafferid;
	private String updownstate;
	private String recorddate;
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
	public String getRecorddate() {
		return recorddate;
	}
	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
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
	public String getUpdownstate() {
		return updownstate;
	}
	public void setUpdownstate(String updownstate) {
		this.updownstate = updownstate;
	}

}
