package com.kj133.entity.vo;



public class LogManagerVO{
	
	/**
	 * 日志管理
	 */
	private static final long serialVersionUID = 1L;

	private String recordid;   //  记录号
	 private String name;      //  用户名
	 private String mydate;    //  时间
	 private String userid;    //  用户号
	 private String myaction;  //  操作
	 private String loginfo;   //  详细情况
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
