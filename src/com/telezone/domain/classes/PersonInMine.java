package com.telezone.domain.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.telezone.util.Common;

public class PersonInMine {
	private Integer cardid;

	private String downtime;
	
	//�¾�ʱ��
	private String betweentime;
	
	//����ʱ��
	private String uptime;
	
	private Integer cardreaderid;

	private String crname;

	// ��վX����
	private String crx = "0";

	// ��վY����
	private String cry = "0";

	// Ա��ID
	private String stafferid;

	// Ա������
	private String staffername;

	private String department;

	private String number;

	// ����ʱ��
	private String intime;

	//����ʱ��
	private String betweentimein;
	
	// ����ʱ��
	private String outtime;

	// ��λ��ID
	private String lid;

	// ��λ������
	private String lname;

	// ��λ��X����
	private String lx = "0";

	// ��λ��Y����
	private String ly = "0";

	// ��ͼID
	private String mapid;

	private String state;
	
	//��Ա�İ�����Ϣ
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
			department = "δ��";
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
			staffername = "δ��";
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
