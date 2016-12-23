package com.telezone.domain.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowBackup {
	
	private String name;
	
	private String dept;
	
	private String recordid;
	
	private String cardid;

	private String cardreaderid;

	private String ground;

	private String starttime;

	private String endtime;

	private String crname;
	
	private String betweentime;
	
	private Date chuanrutime;
	
	private String webx;
	
	private String weby;
	
	private String mapid;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardreaderid() {
		return cardreaderid;
	}

	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) throws Exception{
		if(endtime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(endtime);
			this.endtime = sdf.format(date);
		}else {
			this.endtime = endtime;
		}
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public String getBetweentime() {
		return betweentime;
	}

	public void setBetweentime(String betweentime) {
		this.betweentime = betweentime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(starttime);
		this.starttime = sdf.format(date);
	}

	public Date getChuanrutime() {
		return chuanrutime;
	}

	public void setChuanrutime(Date chuanrutime) {
		this.chuanrutime = chuanrutime;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getWebx() {
		return webx;
	}

	public void setWebx(String webx) {
		this.webx = webx;
	}

	public String getWeby() {
		return weby;
	}

	public void setWeby(String weby) {
		this.weby = weby;
	}

	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
}
