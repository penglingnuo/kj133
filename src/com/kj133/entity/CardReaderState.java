package com.kj133.entity;

//��ͨѶ������ʾ��վ��Ϣ
public class CardReaderState {
	 private String cardreaderid;//��վ��
	 private String shortname;//��վ����
	 private String temperature;//�¶�
	 private String readercount;//����
	
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
