package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Worktype_areadis_topVO extends Staffer {

	/**
	 * 区域人数表
	 */
	private static final long serialVersionUID = 1L;

	

	private String areaid;

	private String areaname;
	private String recordtime;
	private int visitorcount;
	private int minercount;
	
	private int peoplecount;

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

	public int getMinercount() {
		return minercount;
	}

	public void setMinercount(int minercount) {
		this.minercount = minercount;
	}

	public int getPeoplecount() {
		return peoplecount;
	}

	public void setPeoplecount(int peoplecount) {
		this.peoplecount = peoplecount;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public int getVisitorcount() {
		return visitorcount;
	}

	public void setVisitorcount(int visitorcount) {
		this.visitorcount = visitorcount;
	}



	



}
