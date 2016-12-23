package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class DownWellCountVO  extends Staffer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
	private String username;
	private String gro;
	private String type;
	private String downtimes;
	private String stime;
	private String etime;
	public String getDowntimes() {
		return downtimes;
	}
	public void setDowntimes(String downtimes) {
		this.downtimes = downtimes;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
