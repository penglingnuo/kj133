package com.kj133.entity;

//在通讯里面显示分站信息
public class CardReaderState {
	 private String cardreaderid;//分站号
	 private String shortname;//分站名称
	 private String temperature;//温度
	 private String readercount;//总数
	
	 public CardReaderState(String cardreaderid, String shortname, String temperature, String readercount) {
		super();
		this.cardreaderid = cardreaderid;
		this.shortname = shortname;
		this.temperature = temperature;
		this.readercount = readercount;
	}
	public String getCardreaderid() {
		return cardreaderid;
	}
	public void setCardreaderid(String cardreaderid) {
		this.cardreaderid = cardreaderid;
	}
	public String getReadercount() {
		return readercount;
	}
	public void setReadercount(String readercount) {
		this.readercount = readercount;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
