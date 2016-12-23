package com.kj133.entity.vo;

import com.kj133.entity.Locator;

public class Locator_batteriesVO  extends Locator{

	/**
	 * 定位器电池报警
	 */
	private static final long serialVersionUID = 1L;
    private String locatorid;
    private String shortname;
    private String info;
    private String etime;
    private String stime;
    private String startdate;
    private String enddate;
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getLocatorid() {
		return locatorid;
	}
	public void setLocatorid(String locatorid) {
		this.locatorid = locatorid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
}
