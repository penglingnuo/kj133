package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Area_overtime_topVO extends Staffer {

	/**
	 * 区域超时人次统计
	 */
	private static final long serialVersionUID = 1L;

	private String ncount;

	private String areaid;

	private String areaname;

	private String st;

	private String et;

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getEt() {
		return et;
	}

	public void setEt(String et) {
		this.et = et;
	}

	public String getNcount() {
		return ncount;
	}

	public void setNcount(String ncount) {
		this.ncount = ncount;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

}
