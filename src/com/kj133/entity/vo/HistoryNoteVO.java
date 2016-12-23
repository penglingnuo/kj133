package com.kj133.entity.vo;

import com.kj133.entity.CardReader;

public class HistoryNoteVO extends  CardReader{

	/**
	 * 历史记录查询
	 */
	private static final long serialVersionUID = 1L;
	private String count;
	private String cid;
	private String times;
	private String stat;
	private String temper;
	private String ccount;
	private String interruptcount;
	private String ignorecount;
	private String name;
	private String info;
	
	public String getCcount() {
		return ccount;
	}
	public void setCcount(String ccount) {
		this.ccount = ccount;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getIgnorecount() {
		return ignorecount;
	}
	public void setIgnorecount(String ignorecount) {
		this.ignorecount = ignorecount;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInterruptcount() {
		return interruptcount;
	}
	public void setInterruptcount(String interruptcount) {
		this.interruptcount = interruptcount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getTemper() {
		return temper;
	}
	public void setTemper(String temper) {
		this.temper = temper;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

}
