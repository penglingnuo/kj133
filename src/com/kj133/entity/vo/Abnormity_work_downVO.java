package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Abnormity_work_downVO extends Staffer {

	/**
	 * Ա�������쳣��ϸ
	 */
	private static final long serialVersionUID = 1L;

	private String cardid; // ����

	private String stafferid; // Ա�����

	private String locus; // Ҫ��ص�

	private String name; // ����

	private String requiretime; // Ҫ�󵽴�ʱ��

	private String reachtime; // ʵ�ʵ���ʱ��

	private String occupation; // ְ��

	private String uptime; // ����ʱ��

	private String downtime; // �뾮ʱ��

	private String gro; // ����

	private String worktype; // ����

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

	public String getLocus() {
		return locus;
	}

	public void setLocus(String locus) {
		this.locus = locus;
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

	public String getReachtime() {
		return reachtime;
	}

	public void setReachtime(String reachtime) {
		this.reachtime = reachtime;
	}

	public String getRequiretime() {
		return requiretime;
	}

	public void setRequiretime(String requiretime) {
		this.requiretime = requiretime;
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

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

}
