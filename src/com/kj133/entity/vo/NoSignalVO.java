package com.kj133.entity.vo;

import com.kj133.entity.Locator;

public class NoSignalVO extends Locator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String locatorid;
	private String lid;
	private String sname;
	private String times;
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getLocatorid() {
		return locatorid;
	}
	public void setLocatorid(String locatorid) {
		this.locatorid = locatorid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
    
}
