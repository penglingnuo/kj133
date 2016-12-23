package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class upload721 {
	private String cardid;

	private String downcardreaderid;

	private String starttime;

	private String endtime;

	private String stationid;

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getDowncardreaderid() {
		return downcardreaderid;
	}

	public void setDowncardreaderid(String downcardreaderid) {
		this.downcardreaderid = downcardreaderid;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			endtime = sdf.format(sdf.parse(endtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.endtime = endtime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			starttime = sdf.format(sdf.parse(starttime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.starttime = starttime;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

}
