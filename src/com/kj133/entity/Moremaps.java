package com.kj133.entity;

/**
 * 多图用的表
 * @author Administrator
 *
 */
public class Moremaps {
	private String recordid; 
	private String mapid;  	//地图id
	private String mapname;  //地图名称
	private String mapjx;  //地图简写
	private String linesx;  //line的属性（mapconfig.js中配置的）
	private String mapmaxjxd; //最大解析图（mapconfig.js中配置的）
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getMapid() {
		return mapid;
	}
	public void setMapid(String mapid) {
		this.mapid = mapid;
	}
	public String getMapname() {
		return mapname;
	}
	public void setMapname(String mapname) {
		this.mapname = mapname;
	}
	public String getMapjx() {
		return mapjx;
	}
	public void setMapjx(String mapjx) {
		this.mapjx = mapjx;
	}
	public String getLinesx() {
		return linesx;
	}
	public void setLinesx(String linesx) {
		this.linesx = linesx;
	}
	public String getMapmaxjxd() {
		return mapmaxjxd;
	}
	public void setMapmaxjxd(String mapmaxjxd) {
		this.mapmaxjxd = mapmaxjxd;
	}
}
