package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Abnormity_work_topVO extends Staffer {

	/**
	 * �����쳣Ա���嵥
	 */
	private static final long serialVersionUID = 1L;

	private String stafferid; // Ա�����

	private String cardid; // ����

	private String name; // ����

	private String occupation; // ְ��

	private int abnormitylist; // �쳣����

	private String uptime; // ����ʱ��

	private String downtime; // �뾮ʱ��

	private String gro; // ����

	private String worktype; // ����

	public int getAbnormitylist() {
		return abnormitylist;
	}

	public void setAbnormitylist(int abnormitylist) {
		this.abnormitylist = abnormitylist;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getGro() {
		return gro;
	}

	public void setGro(String gro) {
		this.gro = gro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

}
