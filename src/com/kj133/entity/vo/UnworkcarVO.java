package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class UnworkcarVO  extends Staffer{
	
	/**
	 *时段未出勤车辆统计 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String count; // 序号
	 private String cartype;//车类型
	 private String carcount;//总车数
	 private String carname;//车名称
	 private int reckon;  //合计
	 private String unworkodds;//几率
	 
	 
	 
	 private String kqtime;
	 private String uptime;
	 private String downtime; 
	 private String cardid;
	 private String stafferid;
	 private String intime;
	 private String upwelltime;
	 private String username;
	 private String gro;
	 private String worktype;
	 private String worktime;
	 private String downdate;
	 private String incardreader;
	 private String incardreadername;
	 private String inlocator;
	 private String inlocatorname;
	 private String upcardreader;
	 private String upcardreadername;
	 private String uplocator;
	 private String uplocatorname;
	 private String allworktime;
	 private String banci;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getDowndate() {
		return downdate;
	}
	public void setDowndate(String downdate) {
		this.downdate = downdate;
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
	public String getIncardreader() {
		return incardreader;
	}
	public void setIncardreader(String incardreader) {
		this.incardreader = incardreader;
	}
	public String getIncardreadername() {
		return incardreadername;
	}
	public void setIncardreadername(String incardreadername) {
		this.incardreadername = incardreadername;
	}
	public String getInlocator() {
		return inlocator;
	}
	public void setInlocator(String inlocator) {
		this.inlocator = inlocator;
	}
	public String getInlocatorname() {
		return inlocatorname;
	}
	public void setInlocatorname(String inlocatorname) {
		this.inlocatorname = inlocatorname;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getUpcardreader() {
		return upcardreader;
	}
	public void setUpcardreader(String upcardreader) {
		this.upcardreader = upcardreader;
	}
	public String getUpcardreadername() {
		return upcardreadername;
	}
	public void setUpcardreadername(String upcardreadername) {
		this.upcardreadername = upcardreadername;
	}
	public String getUplocator() {
		return uplocator;
	}
	public void setUplocator(String uplocator) {
		this.uplocator = uplocator;
	}
	public String getUplocatorname() {
		return uplocatorname;
	}
	public void setUplocatorname(String uplocatorname) {
		this.uplocatorname = uplocatorname;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getUpwelltime() {
		return upwelltime;
	}
	public void setUpwelltime(String upwelltime) {
		this.upwelltime = upwelltime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getKqtime() {
		return kqtime;
	}
	public void setKqtime(String kqtime) {
		this.kqtime = kqtime;
	}
	public String getAllworktime() {
		return allworktime;
	}
	public void setAllworktime(String allworktime) {
		this.allworktime = allworktime;
	}
	public String getBanci() {
		return banci;
	}
	public void setBanci(String banci) {
		this.banci = banci;
	}
	public String getCarcount() {
		return carcount;
	}
	public void setCarcount(String carcount) {
		this.carcount = carcount;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getUnworkodds() {
		return unworkodds;
	}
	public void setUnworkodds(String unworkodds) {
		this.unworkodds = unworkodds;
	}
	public int getReckon() {
		return reckon;
	}
	public void setReckon(int reckon) {
		this.reckon = reckon;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

}
