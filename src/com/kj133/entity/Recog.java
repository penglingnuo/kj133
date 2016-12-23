package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Recog  extends BaseObject{

	/**
	 *ø®–≈œ¢
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
	private String cardid;
	private String mincardid;
	private String maxcardid;
	private String cardmode;
	private String cardname;
	private String classgroup;
	private String cardstate;
	private String addons;
    private String regdate;	
    private String cardtypename;
    private String modifydate;
    private String ifupdate;
    private String ifpublic;
	public String getIfpublic() {
		return ifpublic;
	}
	public void setIfpublic(String ifpublic) {
		this.ifpublic = ifpublic;
	}
	public String getIfupdate() {
		return ifupdate;
	}
	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}
	public String getAddons() {
		return addons;
	}
	public void setAddons(String addons) {
		this.addons = addons;
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
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getCardstate() {
		return cardstate;
	}
	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}
	public String getCardtypename() {
		return cardtypename;
	}
	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}
	public String getClassgroup() {
		return classgroup;
	}
	public void setClassgroup(String classgroup) {
		this.classgroup = classgroup;
	}

	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getMaxcardid() {
		return maxcardid;
	}
	public void setMaxcardid(String maxcardid) {
		this.maxcardid = maxcardid;
	}
	public String getMincardid() {
		return mincardid;
	}
	public void setMincardid(String mincardid) {
		this.mincardid = mincardid;
	}
    
	
}
