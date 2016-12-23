package com.telezone.domain.classes;

public class GasData implements java.io.Serializable {

	private Integer cardreaderid;

	private String locatorid;

	private String jcbid;

	private String recordtime;

	private String gasConcentration;// 分站或者定位器的真实数据

	private String state;

	private String gasover;// 分站或者定位器的上限数据

	public Integer getCardreaderid() {
		return cardreaderid;
	}

	public void setCardreaderid(Integer cardreaderid) {
		this.cardreaderid = cardreaderid;
	}

	public String getGasConcentration() {
		return gasConcentration;
	}

	public void setGasConcentration(String gasConcentration) {
		float _g = Float.parseFloat(gasConcentration);
		_g = _g / 100;

		this.gasConcentration = Float.toString(_g);
	}

	public String getGasover() {
		return gasover;
	}

	public void setGasover(String gasover) {
		float _g = Float.parseFloat(gasover);
		_g = _g / 100;

		this.gasover = Float.toString(_g);
	}

	public String getJcbid() {
		return jcbid;
	}

	public void setJcbid(String jcbid) {
		this.jcbid = jcbid;
	}

	public String getLocatorid() {
		return locatorid;
	}

	public void setLocatorid(String locatorid) {
		this.locatorid = locatorid;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}