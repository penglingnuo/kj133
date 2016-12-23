package com.kj133.entity;

 

public class Search_Account  {
 
	   private String department_id;//部门编号
	   private String hidden_did;//隐藏部门的id
	   private String department_depict;//部门描述
	   private String department_dname;//部门名字
	   private String group_id;//组别名字
	   private String group_name;//组别名称
	   private String group_depict;//组别描述
	   private String uid;//用户号
	   private String oname;//用户名
	   private String password;//密码
	   private String cratetime;//创建时间
	   private String departmentid;//所属部门id
	   private String groupid;//所属组别id

	public String getDepartment_depict() {
		return department_depict;
	}
	public void setDepartment_depict(String department_depict) {
		this.department_depict = department_depict;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_dname() {
		return department_dname;
	}
	public void setDepartment_dname(String department_dname) {
		this.department_dname = department_dname;
	}
	public String getGroup_depict() {
		return group_depict;
	}
	public void setGroup_depict(String group_depict) {
		this.group_depict = group_depict;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getCratetime() {
		return cratetime;
	}
	public void setCratetime(String cratetime) {
		this.cratetime = cratetime;
	}
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
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getHidden_did() {
		return hidden_did;
	}
	public void setHidden_did(String hidden_did) {
		this.hidden_did = hidden_did;
	}
}
