package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReaderState611 {
	private Short readerId;

	private Short state;

	private String startTime;

	private String endTime;

	public Short getReaderId() {
		return this.readerId;
	}

	public void setReaderId(Short readerId) {
		this.readerId = readerId;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			startTime = sdf.format(sdf.parse(startTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}