package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Ogroup extends BaseObject{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
	private String groupid;
	private String gname;
	private String gdescription;
	private String departmentid;
	private String grouppopedom;
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getGdescription() {
		return gdescription;
	}
	public void setGdescription(String gdescription) {
		this.gdescription = gdescription;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGrouppopedom() {
		return grouppopedom;
	}
	public void setGrouppopedom(String grouppopedom) {
		this.grouppopedom = grouppopedom;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
}
