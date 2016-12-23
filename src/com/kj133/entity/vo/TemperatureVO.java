package com.kj133.entity.vo;

import com.kj133.entity.CardReader;

public class TemperatureVO  extends CardReader{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String allcount;
	 private String cid;
	 private String times;
	 private String sta;
	 private String temp;
	 private String count;
	 private String inter;
	 private String ignor;
	 private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getIgnor() {
		return ignor;
	}
	public void setIgnor(String ignor) {
		this.ignor = ignor;
	}
	public String getInter() {
		return inter;
	}
	public void setInter(String inter) {
		this.inter = inter;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getAllcount() {
		return allcount;
	}
	public void setAllcount(String allcount) {
		this.allcount = allcount;
	}

}
