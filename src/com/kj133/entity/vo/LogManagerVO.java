package com.kj133.entity.vo;



public class LogManagerVO{
	
	/**
	 * ��־����
	 */
	private static final long serialVersionUID = 1L;

	private String recordid;   //  ��¼��
	 private String name;      //  �û���
	 private String mydate;    //  ʱ��
	 private String userid;    //  �û���
	 private String myaction;  //  ����
	 private String loginfo;   //  ��ϸ���
	public String getLoginfo() {
		return loginfo;
	}
	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}
	public String getMyaction() {
		return myaction;
	}
	public void setMyaction(String myaction) {
		this.myaction = myaction;
	}
	public String getMydate() {
		return mydate;
	}
	public void setMydate(String mydate) {
		this.mydate = mydate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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




}
