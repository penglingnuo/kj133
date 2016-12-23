package com.kj133.entity;

import java.sql.Blob;

public class FleeHelp {
     
	/**
	 * Ó¦¼±°ïÖú
	 */
	private String recordid;
	private String mapid;
	private String mapinfo;
	private Blob mapdata;
	private Blob compressdata;
	public Blob getCompressdata() {
		return compressdata;
	}
	public void setCompressdata(Blob compressdata) {
		this.compressdata = compressdata;
	}
	public Blob getMapdata() {
		return mapdata;
	}
	public void setMapdata(Blob mapdata) {
		this.mapdata = mapdata;
	}
	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	public String getMapinfo() {
		return mapinfo;
	}
	public void setMapinfo(String mapinfo) {
		this.mapinfo = mapinfo;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
}
 
 
 