package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Depdownmonth  extends BaseObject{

	/**
	 * 部门下井月报
	 */
	private static final long serialVersionUID = 1L;
	private String count;
	private int recordid;
	private String depname;
	private int peoplecount;
	private int downtimesa;
	private int downtimesb;
	private int downdaysa;
	private int downdaysb;
	private String  downintervala;
	private String downintervalb;
	private String avgintervala;
	private String  avgintervalb;
	private String avgtimesa;
	private String avgtimesb;
	private int below2timesa;
	private int below2timesb;
	private int yeartimes;
	private String userid;
	public String getAvgintervala() {
		return avgintervala;
	}
	public void setAvgintervala(String avgintervala) {
		this.avgintervala = avgintervala;
	}
	public String getAvgintervalb() {
		return avgintervalb;
	}
	public void setAvgintervalb(String avgintervalb) {
		this.avgintervalb = avgintervalb;
	}
 
	public int getBelow2timesa() {
		return below2timesa;
	}
	public void setBelow2timesa(int below2timesa) {
		this.below2timesa = below2timesa;
	}
	public int getBelow2timesb() {
		return below2timesb;
	}
	public void setBelow2timesb(int below2timesb) {
		this.below2timesb = below2timesb;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public int getDowndaysa() {
		return downdaysa;
	}
	public void setDowndaysa(int downdaysa) {
		this.downdaysa = downdaysa;
	}
	public int getDowndaysb() {
		return downdaysb;
	}
	public void setDowndaysb(int downdaysb) {
		this.downdaysb = downdaysb;
	}
	public String getDownintervala() {
		return downintervala;
	}
	public void setDownintervala(String downintervala) {
		this.downintervala = downintervala;
	}
	public String getDownintervalb() {
		return downintervalb;
	}
	public void setDownintervalb(String downintervalb) {
		this.downintervalb = downintervalb;
	}
	public int getDowntimesa() {
		return downtimesa;
	}
	public void setDowntimesa(int downtimesa) {
		this.downtimesa = downtimesa;
	}
	public int getDowntimesb() {
		return downtimesb;
	}
	public void setDowntimesb(int downtimesb) {
		this.downtimesb = downtimesb;
	}
	public int getPeoplecount() {
		return peoplecount;
	}
	public void setPeoplecount(int peoplecount) {
		this.peoplecount = peoplecount;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getYeartimes() {
		return yeartimes;
	}
	public void setYeartimes(int yeartimes) {
		this.yeartimes = yeartimes;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getAvgtimesa() {
		return avgtimesa;
	}
	public void setAvgtimesa(String avgtimesa) {
		this.avgtimesa = avgtimesa;
	}
	public String getAvgtimesb() {
		return avgtimesb;
	}
	public void setAvgtimesb(String avgtimesb) {
		this.avgtimesb = avgtimesb;
	}
	 
}
