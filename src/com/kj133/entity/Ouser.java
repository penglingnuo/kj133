package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Ouser extends BaseObject {

	/**
	 * 用户的基本信息
	 */
	private static final long serialVersionUID = 1L;
    private String recordid;
    private String userid;
    private String oname;
    private String opassword;
    private String departmentid;
    private String groupid;
    private String userpopedom;
    private String quserpopedom;
    private String createtime;
    private String modifytime;
    private int userbind;

	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}


	public String getOpassword() {
		return opassword;
	}
	public void setOpassword(String opassword) {
		this.opassword = opassword;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpopedom() {
		return userpopedom;
	}
	public void setUserpopedom(String userpopedom) {
		this.userpopedom = userpopedom;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	public String getQuserpopedom() {
		return quserpopedom;
	}
	public void setQuserpopedom(String quserpopedom) {
		this.quserpopedom = quserpopedom;
	}
	public int getUserbind() {
		return userbind;
	}
	public void setUserbind(int userbind) {
		this.userbind = userbind;
	}


	
}
