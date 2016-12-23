package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VReaderData {
	private Integer cardreaderid;

	private String recordtime;

	private Integer state;

	private Integer temperature;

	private Integer cardcount;

	private Integer interruptcount;

	private Integer ignorecount;

	public Integer getCardcount() {
		return cardcount;
	}

	public void setCardcount(Integer cardcount) {
		this.cardcount = cardcount;
	}

	public Integer getCardreaderid() {
		return cardreaderid;
	}

	public void setCardreaderid(Integer cardreaderid) {
		this.cardreaderid = cardreaderid;
	}

	public Integer getIgnorecount() {
		return ignorecount;
	}

	public void setIgnorecount(Integer ignorecount) {
		this.ignorecount = ignorecount;
	}

	public Integer getInterruptcount() {
		return interruptcount;
	}

	public void setInterruptcount(Integer interruptcount) {
		this.interruptcount = interruptcount;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			recordtime = sdf.format(sdf.parse(recordtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.recordtime = recordtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

}
