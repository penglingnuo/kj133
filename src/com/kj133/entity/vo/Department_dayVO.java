package com.kj133.entity.vo;

import java.io.Serializable;
import java.util.List;

public class Department_dayVO implements Serializable{

   
	private static final long serialVersionUID = -1005830936019757417L;
	private int count;
	private String department;
	private String department1;
	private String department2;
	private String allcount;
	private String peoplecount;
	private String worktime;
	private String avgworktime;
	private String avgdowncount;
	private int dep;
	private int dep1;
	
	private List deptList; 
	private List groupList;
	private List userList; 
	
	private String gro;
	public String getAllcount() {
		return allcount;
	}
	public void setAllcount(String allcount) {
		this.allcount = allcount;
	}
	public String getAvgdowncount() {
		return avgdowncount;
	}
	public void setAvgdowncount(String avgdowncount) {
		this.avgdowncount = avgdowncount;
	}
	public String getAvgworktime() {
		return avgworktime;
	}
	public void setAvgworktime(String avgworktime) {
		this.avgworktime = avgworktime;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPeoplecount() {
		return peoplecount;
	}
	public void setPeoplecount(String peoplecount) {
		this.peoplecount = peoplecount;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getGro() {
		return gro;
	}
	public void setGro(String gro) {
		this.gro = gro;
	}
	public List getDeptList() {
		return deptList;
	}
	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}
	public List getGroupList() {
		return groupList;
	}
	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}
	public List getUserList() {
		return userList;
	}
	public void setUserList(List userList) {
		this.userList = userList;
	}
	public int getDep() {
		return dep;
	}
	public void setDep(int dep) {
		this.dep = dep;
	}
	public String getDepartment1() {
		return department1;
	}
	public void setDepartment1(String department1) {
		this.department1 = department1;
	}
	public int getDep1() {
		return dep1;
	}
	public void setDep1(int dep1) {
		this.dep1 = dep1;
	}
	public String getDepartment2() {
		return department2;
	}
	public void setDepartment2(String department2) {
		this.department2 = department2;
	}
	
 
}
