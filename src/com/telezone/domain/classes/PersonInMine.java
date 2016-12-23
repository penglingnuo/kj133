package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.telezone.util.Common;

public class PersonInMine {
	private Integer cardid;

	private String downtime;
	
	//下井时长
	private String betweentime;
	
	//升井时间
	private String uptime;
	
	private Integer cardreaderid;

	private String crname;

	// 基站X坐标
	private String crx = "0";

	// 基站Y坐标
	private String cry = "0";

	// 员工ID
	private String stafferid;

	// 员工名称
	private String staffername;

	private String department;

	private String number;

	// 进入时间
	private String intime;

	//进入时长
	private String betweentimein;
	
	// 出来时间
	private String outtime;

	// 定位器ID
	private String lid;

	// 定位器名称
	private String lname;

	// 定位器X坐标
	private String lx = "0";

	// 定位器Y坐标
	private String ly = "0";

	// 地图ID
	private String mapid;

	private String state;
	
	//人员的班组信息
	private String group;
	
	private String personincardreadernumber;
	
	private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}

	public Integer getCardreaderid() {
		return cardreaderid;
	}

	public void setCardreaderid(Integer cardreaderid) {
		this.cardreaderid = cardreaderid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		if (department.equals("_null")) {
			department = "未绑定";
		}

		this.department = department;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			downtime = sdf.format(sdf.parse(downtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Common c = new Common();
		if(this.getUptime() == null || this.getUptime().length() == 0) {
			this.setBetweentime(c.betweenTime(downtime));
		}else {
			this.setBetweentime(c.betweenTime(downtime,uptime));
		}
		
		this.downtime = downtime;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCrx() {
		return crx;
	}

	public void setCrx(String crx) {
		this.crx = crx;
	}

	public String getCry() {
		return cry;
	}

	public void setCry(String cry) {
		this.cry = cry;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public String getStaffername() {
		return staffername;
	}

	public void setStaffername(String staffername) {
		if (staffername.equals("_null")) {
			staffername = "未绑定";
		}
		this.staffername = staffername;
	}

	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			intime = sdf.format(sdf.parse(intime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Common c = new Common();
		this.setBetweentimein(c.betweenTime(intime));
		
		this.intime = intime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getPersonincardreadernumber() {
		return personincardreadernumber;
	}

	public void setPersonincardreadernumber(String personincardreadernumber) {
		this.personincardreadernumber = personincardreadernumber;
	}

	public String getBetweentime() {
		return betweentime;
	}

	public void setBetweentime(String betweentime) {
		this.betweentime = betweentime;
	}

	public String getBetweentimein() {
		return betweentimein;
	}

	public void setBetweentimein(String betweentimein) {
		this.betweentimein = betweentimein;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
}
