package com.kj133.entity;

public class Search_TransitTrctic {
	
	private String caption;//标题
    private String stime;
    private String etime;
    private String[] cardreaderx;
    private String[] locatorx;
    private String[] stafferx;
    private String pass;//禁止通行
    private String passmode;//时间设定
    
    
	public String getPassmode() {
		return passmode;
	}
	public void setPassmode(String passmode) {
		this.passmode = passmode;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String[] getCardreaderx() {
		return cardreaderx;
	}
	public void setCardreaderx(String[] cardreaderx) {
		this.cardreaderx = cardreaderx;
	}
	public String[] getLocatorx() {
		return locatorx;
	}
	public void setLocatorx(String[] locatorx) {
		this.locatorx = locatorx;
	}
	public String[] getStafferx() {
		return stafferx;
	}
	public void setStafferx(String[] stafferx) {
		this.stafferx = stafferx;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
