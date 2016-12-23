package com.telezone.Test;

import java.io.Serializable;

public class OGroup implements Serializable{
	private static final long serialVersionUID = 6543197600841110304L;
	private Integer recordid;
	private String groupid;
	private String gname;
	private String gdescription;
	private String departmentid;
	private String grouppopedom;
	private String qgrouppopedom;
	private String keyid;
	
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
	public String getQgrouppopedom() {
		return qgrouppopedom;
	}
	public void setQgrouppopedom(String qgrouppopedom) {
		this.qgrouppopedom = qgrouppopedom;
	}
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	
	
}
