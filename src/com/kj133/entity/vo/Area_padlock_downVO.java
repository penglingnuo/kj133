package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Area_padlock_downVO extends Staffer {

	/**
	 * 区域禁入人员明细
	 */
	private static final long serialVersionUID = 1L;
	
	private String aid;
	private String aname;
	private String name;
	private String cid;
	private String gro;
	private String worktype;
	private String starttime;
	private String endtime;
	private String Stl;
	
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getStl() {
		return Stl;
	}
	public void setStl(String stl) {
		Stl = stl;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

}
