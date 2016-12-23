package com.kj133.entity;

 
import java.io.Serializable;

public class Routing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String recordid;
	 private String code;
	 private String routename;
	 private String routeinfo;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	 
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getRouteinfo() {
		return routeinfo;
	}
	public void setRouteinfo(String routeinfo) {
		this.routeinfo = routeinfo;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}

}
