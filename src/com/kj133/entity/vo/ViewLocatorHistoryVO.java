package com.kj133.entity.vo;

import com.kj133.entity.Locatorhis;;

public class ViewLocatorHistoryVO  extends Locatorhis {

	/**
	 * 定位器历史查询
	 */
	private static final long serialVersionUID = 1L;
    

	private String loca_startdate;
	private String loca_modifydate;
	private String loca_locatorid;
	private String loca_lname;
	private String loca_shortname;
	private String loca_x;
	private String loca_y;
	private String loca_z;
	private String loca_regdate;
	private String loca_mapid;
	public String getLoca_lname() {
		return loca_lname;
	}
	public void setLoca_lname(String loca_lname) {
		this.loca_lname = loca_lname;
	}
	public String getLoca_locatorid() {
		return loca_locatorid;
	}
	public void setLoca_locatorid(String loca_locatorid) {
		this.loca_locatorid = loca_locatorid;
	}
	public String getLoca_mapid() {
		return loca_mapid;
	}
	public void setLoca_mapid(String loca_mapid) {
		this.loca_mapid = loca_mapid;
	}
	public String getLoca_modifydate() {
		return loca_modifydate;
	}
	public void setLoca_modifydate(String loca_modifydate) {
		this.loca_modifydate = loca_modifydate;
	}
	public String getLoca_regdate() {
		return loca_regdate;
	}
	public void setLoca_regdate(String loca_regdate) {
		this.loca_regdate = loca_regdate;
	}
	public String getLoca_shortname() {
		return loca_shortname;
	}
	public void setLoca_shortname(String loca_shortname) {
		this.loca_shortname = loca_shortname;
	}
	public String getLoca_startdate() {
		return loca_startdate;
	}
	public void setLoca_startdate(String loca_startdate) {
		this.loca_startdate = loca_startdate;
	}
	public String getLoca_x() {
		return loca_x;
	}
	public void setLoca_x(String loca_x) {
		this.loca_x = loca_x;
	}
	public String getLoca_y() {
		return loca_y;
	}
	public void setLoca_y(String loca_y) {
		this.loca_y = loca_y;
	}
	public String getLoca_z() {
		return loca_z;
	}
	public void setLoca_z(String loca_z) {
		this.loca_z = loca_z;
	}
	
}
