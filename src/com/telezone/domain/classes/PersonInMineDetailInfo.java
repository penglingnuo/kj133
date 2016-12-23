package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.telezone.util.Common;

public class PersonInMineDetailInfo {
	private String map;

	private String worktype;

	private Integer cardid;

	private String stafferid;

	private String staffername;

	private String staffergroup;

	private String stafferdepartment;

	private String cardreader;

	private String cardreadername;

	private String locator;

	private String locatorname;

	private String downtime;

	private String uptime;

	private String betweentime;

	private String antenna;

	private String islocator;

	private String state;

	private String lastinfotime;

	private String inlocatortime;

	private String stayintercal;

	private String areaname;
	
	private String cwebx;
	
	private String cweby;
	
	private String lwebx;
	
	private String lweby;

	public String getAntenna() {
		return antenna;
	}

	public void setAntenna(String antenna) {
		this.antenna = antenna;
	}

	public String getBetweentime() {
		return betweentime;
	}

	public void setBetweentime(String betweentime) {
		this.betweentime = betweentime;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}

	public String getCardreader() {
		return cardreader;
	}

	public void setCardreader(String cardreader) {
		this.cardreader = cardreader;
	}

	public String getCardreadername() {
		return cardreadername;
	}

	public void setCardreadername(String cardreadername) {
		this.cardreadername = cardreadername;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		if (downtime == null) {
			downtime = "ÎÞ";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				downtime = sdf.format(sdf.parse(downtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Common c = new Common();
		this.setBetweentime(c.betweenTime(downtime));
		
		this.downtime = downtime;
	}

	public String getInlocatortime() {
		return inlocatortime;
	}

	public void setInlocatortime(String inlocatortime) {
		this.inlocatortime = inlocatortime;
	}

	public String getIslocator() {
		return islocator;
	}

	public void setIslocator(String islocator) {
		this.islocator = islocator;
	}

	public String getLastinfotime() {
		return lastinfotime;
	}

	public void setLastinfotime(String lastinfotime) {
		this.lastinfotime = lastinfotime;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getLocatorname() {
		return locatorname;
	}

	public void setLocatorname(String locatorname) {
		this.locatorname = locatorname;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getStaffergroup() {
		return staffergroup;
	}

	public void setStaffergroup(String staffergroup) {
		if (staffergroup == null) {
			staffergroup = "Î´°ó¶¨";
		}
		this.staffergroup = staffergroup;
	}

	public String getStaffername() {
		return staffername;
	}

	public void setStaffername(String staffername) {
		if (staffername == null) {
			staffername = "Î´°ó¶¨";
		}
		this.staffername = staffername;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStayintercal() {
		return stayintercal;
	}

	public void setStayintercal(String stayintercal) {
		this.stayintercal = stayintercal;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		if (worktype == null) {
			worktype = "ÆäËü";
		}
		this.worktype = worktype;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		if (stafferid == null) {
			stafferid = "Î´°ó¶¨";
		}

		this.stafferid = stafferid;
	}

	public String getStafferdepartment() {
		return stafferdepartment;
	}

	public void setStafferdepartment(String stafferdepartment) {
		if (stafferdepartment == null) {
			stafferdepartment = "Î´°ó¶¨";
		}

		this.stafferdepartment = stafferdepartment;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		if (uptime == null) {
			uptime = "Î´Éý¾®";
		}

		this.uptime = uptime;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getCwebx() {
		return cwebx;
	}

	public void setCwebx(String cwebx) {
		this.cwebx = cwebx;
	}

	public String getCweby() {
		return cweby;
	}

	public void setCweby(String cweby) {
		this.cweby = cweby;
	}

	public String getLwebx() {
		return lwebx;
	}

	public void setLwebx(String lwebx) {
		this.lwebx = lwebx;
	}

	public String getLweby() {
		return lweby;
	}

	public void setLweby(String lweby) {
		this.lweby = lweby;
	}
}
