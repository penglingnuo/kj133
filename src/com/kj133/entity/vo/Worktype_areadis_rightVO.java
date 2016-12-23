package com.kj133.entity.vo;

import com.kj133.entity.Staffer;

public class Worktype_areadis_rightVO extends Staffer {

	/**
	 * 工种区域分布表---区域分布
	 */
	private static final long serialVersionUID = 1L;
	
	private String worktype;
	private int cishu;
	private int mans;
	public int getCishu() {
		return cishu;
	}
	public void setCishu(int cishu) {
		this.cishu = cishu;
	}
	public int getMans() {
		return mans;
	}
	public void setMans(int mans) {
		this.mans = mans;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	

	


}
