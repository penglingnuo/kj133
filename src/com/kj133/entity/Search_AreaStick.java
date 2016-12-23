package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Search_AreaStick extends BaseObject{
       
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stime;
	 private String etime;
	 private String gro;
	 private String type;
	 private String sid;
	 private String cid;
	 private String minstime;
	 private String maxstime;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaxstime() {
		return maxstime;
	}
	public void setMaxstime(String maxstime) {
		this.maxstime = maxstime;
	}
	public String getMinstime() {
		return minstime;
	}
	public void setMinstime(String minstime) {
		this.minstime = minstime;
	}
}
