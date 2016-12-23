package com.basic.entity.vo;
/**
 * 历史信息查询轨迹
 * @author Administrator
 *
 */
public class WorkAttHisAreaVO {

	/** ---------------行走轨迹明细----------------------------------- */
	private String starttime;// 进入分站时间
	private String endtime;// 离开分站时间
	private String stayinterval;// 分站停留时间
	private String crname;// 分站名称

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStayinterval() {
		return stayinterval;
	}

	public void setStayinterval(String stayinterval) {
		this.stayinterval = stayinterval;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}

}
