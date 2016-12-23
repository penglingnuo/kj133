package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class EditCardVO  extends  Staffer{

	/**
	 * ±‡º≠ø®¡¨Ω”≤È—Ø
	 */
	private static final long serialVersionUID = 1L;
	private String cardid;
	private String cardmode;
	private String worktype;
	private String name;
	private String gro;
	private String cardstate;
	private String regdate;
	private String ifupdate;
	private String modifydate;
	public String getIfupdate() {
		return ifupdate;
	}
	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}
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
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
}
