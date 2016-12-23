package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class OperateCard extends BaseObject{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardid;
	 private String cardmode;
	 private String cardstate;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardmode() {
		return cardmode;
	}
	public void setCardmode(String cardmode) {
		this.cardmode = cardmode;
	}
	public String getCardstate() {
		return cardstate;
	}
	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}

}
