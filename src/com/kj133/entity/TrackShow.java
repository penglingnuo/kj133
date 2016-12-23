package com.kj133.entity;

public class TrackShow {
	private int allcount;
	private String worktype;
	private String cardid;
	private String username;
	private String gro;
	private String cardreaderid;
	private String shortname;
	private String locatorid;
	private String lname;
	private String antenna;
	private String state;
	public TrackShow(String worktype, String cardid, String username, String gro, String cardreaderid, String shortname, String locatorid, String lname, String antenna, String state) {
		super();
		this.worktype = worktype;
		this.cardid = cardid;
		this.username = username;
		this.gro = gro;
		this.cardreaderid = cardreaderid;
		this.shortname = shortname;
		this.locatorid = locatorid;
		this.lname = lname;
		this.antenna = antenna;
		this.state = state;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
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
	public String getAntenna() {
		return antenna;
	}
	public void setAntenna(String antenna) {
		this.antenna = antenna;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAllcount() {
		return allcount;
	}
	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}
 
    
}
