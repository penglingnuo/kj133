package com.kj133.entity;

public class Search_Pworkset {
	
	 /**
	  *  ��Ա��������
	  */
	 private String sid;  //Ա����
	 private String em;  //Ա��
	 private String worktype; //����
	 private String mintime;//��С����ʱ��
	 private String zuhe;//�ص����ã�ID������
	 private String appointminute;//ָ��ʱ��
	 private String bantype;//������
	 private String maxtime;//ָ��ʱ��
	public String getBantype() {
		return bantype;
	}
	public void setBantype(String bantype) {
		this.bantype = bantype;
	}
	public String getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}
	public String getAppointminute() {
		return appointminute;
	}
	public void setAppointminute(String appointminute) {
		this.appointminute = appointminute;
	}
	public String getZuhe() {
		return zuhe;
	}
	public void setZuhe(String zuhe) {
		this.zuhe = zuhe;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getMintime() {
		return mintime;
	}
	public void setMintime(String mintime) {
		this.mintime = mintime;
	}
	public String getEm() {
		return em;
	}
	public void setEm(String em) {
		this.em = em;
	}

}
