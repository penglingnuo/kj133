package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class UnWorkVO extends Staffer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sid;// 员工编号

	private String username;// 姓名

	private String gro;// 班组

	private String type;// 工种

	private String cid;// 卡号

	private String oation;// 职务


	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getOation() {
		return oation;
	}

	public void setOation(String oation) {
		this.oation = oation;
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

	// public String getStime() {
	// return stime;
	// }
	// public void setStime(String stime) {
	// this.stime = stime;
	// }
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
