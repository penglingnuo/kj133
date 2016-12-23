package com.telezone.domain.classes;

public class AlarmInfoObject {
	private String alarmInfo;

	private String stafferName;

	private String subStation;

	private String place = "нч";

	private String state = "нч";

	public String getAlarmInfo() {
		return alarmInfo;
	}

	public void setAlarmInfo(String alarmInfo) {
		this.alarmInfo = alarmInfo;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStafferName() {
		return stafferName;
	}

	public void setStafferName(String stafferName) {
		this.stafferName = stafferName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubStation() {
		return subStation;
	}

	public void setSubStation(String subStation) {
		this.subStation = subStation;
	}

}
