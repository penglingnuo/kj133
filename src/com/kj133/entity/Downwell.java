package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Downwell  extends BaseObject{

	/**
	 * ¾®ÏÂ¿¼ÇÚ
	 */
	private static final long serialVersionUID = 1L;
    private String recordid;
    private String operator;
    private String cardid;
    private String cardname;
    private String cardtypename;
    private String starttime;
    private String endtime;
    private String downtimes;
    private String classgroup;
    private String stafferid;
    private String userid;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardtypename() {
		return cardtypename;
	}
	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}
	public String getClassgroup() {
		return classgroup;
	}
	public void setClassgroup(String classgroup) {
		this.classgroup = classgroup;
	}
	public String getDowntimes() {
		return downtimes;
	}
	public void setDowntimes(String downtimes) {
		this.downtimes = downtimes;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
