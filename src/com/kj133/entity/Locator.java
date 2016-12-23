package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Locator extends BaseObject{

	/**
	 * ¶¨Î»Æ÷
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
	private String locatorid;
	private String lname;
	private String shortname;
	private String x;
	private String y;
	private String regdate;
	private String mapid;
	private String modifydate;
	private String ground;
	private String state;
	private String ifupdate;
	private String z;
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	public String getGround() {
		return ground;
	}
	public void setGround(String ground) {
		this.ground = ground;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLocatorid() {
		return locatorid;
	}
	public void setLocatorid(String locatorid) {
		this.locatorid = locatorid;
	}
	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getIfupdate() {
		return ifupdate;
	}
	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}

}
