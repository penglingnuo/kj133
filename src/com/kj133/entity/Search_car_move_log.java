package com.kj133.entity;

//时段未出勤车辆统计

public class Search_car_move_log {

	// private String sid;

	private String stime;

	private String etime;

	private String cartype;

	private String cardid;
	private String minstime;
	private String maxstime;

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	// private String gro;
	// private String type;
	// private String banci;
	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	/*
	 * public String getGro() { return gro; }
	 * 
	 * public void setGro(String gro) { this.gro = gro; }
	 * 
	 * public String getSid() { return sid; }
	 * 
	 * public void setSid(String sid) { this.sid = sid; }
	 */
	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getMaxstime() {
		return maxstime;
	}

	public void setMaxstime(String maxstime) {
		this.maxstime = maxstime;
	}

	public String getMinstime() {
		return minstime;
	}

	public void setMinstime(String minstime) {
		this.minstime = minstime;
	}

	/*
	 * public String getType() { return type; }
	 * 
	 * public void setType(String type) { this.type = type; }
	 * 
	 * public String getBanci() { return banci; }
	 * 
	 * public void setBanci(String banci) { this.banci = banci; }
	 */

}
