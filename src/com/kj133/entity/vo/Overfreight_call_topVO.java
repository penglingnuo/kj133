package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Overfreight_call_topVO extends Staffer {

	/**
	 * 区域超员统计
	 */
	private static final long serialVersionUID = 1L;

	private int acount;

	private String aid;

	private String aname;
	private String otype;
	private String os;
	private String oe;
	
	private String atime;

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}



	public String getOe() {
		return oe;
	}

	public void setOe(String oe) {
		this.oe = oe;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOtype() {
		return otype;
	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

	



}
