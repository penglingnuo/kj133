package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Area_overtime_downVO extends Staffer {

	/**
	 * ÇøÓò³¬Ê±Ã÷Ï¸
	 */
	private static final long serialVersionUID = 1L;
	
	private String areaid;
	private String areaname;
	private String cardid;
	private String name;
	private String starttime;
	private String endtime;
	private String stayinterval;
	private String limittime;
	private String downtime;
	private String settletime;
	private String uptime;
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
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getLimittime() {
		return limittime;
	}
	public void setLimittime(String limittime) {
		this.limittime = limittime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSettletime() {
		return settletime;
	}
	public void setSettletime(String settletime) {
		this.settletime = settletime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getStayinterval() {
		return stayinterval;
	}
	public void setStayinterval(String stayinterval) {
		this.stayinterval = stayinterval;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

/*	private String carname;

	private String count;

	private String carcount;

	private String cartype;

	private String intime;
	
	private String cardid;

	private String upwelltime;

	private String kqtime;

	private String uptime;

	private String downtime;

	private String worktime;

	private String stafferid;

	private String username;

	private String gro;

	private String worktype;

	

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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}*/

}
