package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Card_batteriesVO extends  Staffer {

	/**
	 * ¿¨µç³Ø±¨¾¯
	 */
	private static final long serialVersionUID = 1L;
	private String cardid;
	private String username;
	private String gro;
	private String info;
	private String maxtime;
	private String mintime;
	private String cardname;
	
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}
	public String getMintime() {
		return mintime;
	}
	public void setMintime(String mintime) {
		this.mintime = mintime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
