package com.kj133.entity.vo;

public class PworksetVO {

      private String stafferid;
      private String name;
      private String cardid;
      private String group;
      private String worktype;
      private String minworktime;
      private String pinyin;
      private String id;
      private String idtype;
      private String shortnamea;
      private String appointminute;
 	 private String bantype;//班类型
	 private int maxtime;//指定时间
	 private int mintime;//指定时间
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getMinworktime() {
		return minworktime;
	}
	public void setMinworktime(String minworktime) {
		this.minworktime = minworktime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStafferid() {
		return stafferid;
	}
	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getAppointminute() {
		return appointminute;
	}
	public void setAppointminute(String appointminute) {
		this.appointminute = appointminute;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getShortnamea() {
		return shortnamea;
	}
	public void setShortnamea(String shortnamea) {
		this.shortnamea = shortnamea;
	}
	public String getBantype() {
		return bantype;
	}
	public void setBantype(String bantype) {
		this.bantype = bantype;
	}
	public int getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(int maxtime) {
		this.maxtime = maxtime;
	}
	public int getMintime() {
		return mintime;
	}
	public void setMintime(int mintime) {
		this.mintime = mintime;
	}
      
}
