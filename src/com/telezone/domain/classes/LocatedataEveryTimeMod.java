package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocatedataEveryTimeMod {
	private Integer cardid;

	private Integer cardreaderid;

	private Integer locatorid;

	private Integer mapid;

	private Integer rx;

	private Integer ry;

	private Integer lx;

	private Integer ly;

	private Integer state;

	private String starttime;

	private Integer endby;

	private Integer startby;

	private Integer antenna;

	private String stafferId;

	public Integer getCardid() {
		return this.cardid;
	}

	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}

	public Integer getCardreaderid() {
		return this.cardreaderid;
	}

	public void setCardreaderid(Integer cardreaderid) {
		this.cardreaderid = cardreaderid;
	}

	public Integer getLocatorid() {
		return this.locatorid;
	}

	public void setLocatorid(Integer locatorid) {
		this.locatorid = locatorid;
	}

	public Integer getMapid() {
		return this.mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public Integer getRx() {
		return this.rx;
	}

	public void setRx(Integer rx) {
		this.rx = rx;
	}

	public Integer getRy() {
		return this.ry;
	}

	public void setRy(Integer ry) {
		this.ry = ry;
	}

	public Integer getLx() {
		return this.lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	public Integer getLy() {
		return this.ly;
	}

	public void setLy(Integer ly) {
		this.ly = ly;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getEndby() {
		return this.endby;
	}

	public void setEndby(Integer endby) {
		this.endby = endby;
	}

	public Integer getStartby() {
		return this.startby;
	}

	public void setStartby(Integer startby) {
		this.startby = startby;
	}

	public Integer getAntenna() {
		return this.antenna;
	}

	public void setAntenna(Integer antenna) {
		this.antenna = antenna;
	}

	public String getStafferId() {
		return this.stafferId;
	}

	public void setStafferId(String stafferId) {
		this.stafferId = stafferId;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			starttime = sdf.format(sdf.parse(starttime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.starttime = starttime;
	}

}
