package com.kj133.entity;

public class Search_emphases_report {

	/**
	 * 重点考勤日月明细报
	 */
	private String stime;

	private String daytime;

	private String monthtime;

	private String gro;

	private String cardid;

	private String isChoose;// 判断是月报还是日报

	private String isDayOrMonth;

	public String getIsDayOrMonth() {
		return isDayOrMonth;
	}

	public void setIsDayOrMonth(String isDayOrMonth) {
		this.isDayOrMonth = isDayOrMonth;
	}

	public String getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getGro() {
		return gro;
	}

	public void setGro(String gro) {
		this.gro = gro;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getDaytime() {
		return daytime;
	}

	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	public String getMonthtime() {
		return monthtime;
	}

	public void setMonthtime(String monthtime) {
		this.monthtime = monthtime;
	}

}
