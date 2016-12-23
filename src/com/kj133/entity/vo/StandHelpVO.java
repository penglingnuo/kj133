package com.kj133.entity.vo;

public class StandHelpVO {
  /**
   * 分站报警信息
   * */
	
	private String cardreaderid;
	private String shortname;
	private String info;
	private String mintime;
	private String maxtime;
	private String alarmtimes;
	private String crname;
	private String areaname;
	private String recordtime;
	public String getAlarmtimes() {
		return alarmtimes;
	}
	public void setAlarmtimes(String alarmtimes) {
		this.alarmtimes = alarmtimes;
	}
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}
	public String getMintime() {
		return mintime;
	}
	public void setMintime(String mintime) {
		this.mintime = mintime;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getCrname() {
		return crname;
	}
	public void setCrname(String crname) {
		this.crname = crname;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

}
