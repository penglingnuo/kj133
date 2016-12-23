package com.kj133.entity;

import org.speedframework.entity.BaseObject;
import java.sql.Blob;
public class WeizhidisList extends BaseObject{

	/**
	 * µÿÕºπ‹¿Ì
	 */
	private static final long serialVersionUID = 1L;
	private String recordid;
	private String mapid;
	private String mapinfo;
	private Blob mapdata;
	private String scale;
	private String mapname;
	private String maplevel;
	private String parentid;
	private String scaletoparentx;
	private String scaletoparenty;
	private String offsetx;
	private String offsety;
	private Blob compressdata;
	private String iscad;
    private String ifupdate;
	public String getIscad() {
		return iscad;
	}
	public void setIscad(String iscad) {
		this.iscad = iscad;
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
	public String getMaplevel() {
		return maplevel;
	}
	public void setMaplevel(String maplevel) {
		this.maplevel = maplevel;
	}
	public String getMapname() {
		return mapname;
	}
	public void setMapname(String mapname) {
		this.mapname = mapname;
	}
	public String getOffsetx() {
		return offsetx;
	}
	public void setOffsetx(String offsetx) {
		this.offsetx = offsetx;
	}
	public String getOffsety() {
		return offsety;
	}
	public void setOffsety(String offsety) {
		this.offsety = offsety;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getScaletoparentx() {
		return scaletoparentx;
	}
	public void setScaletoparentx(String scaletoparentx) {
		this.scaletoparentx = scaletoparentx;
	}
	 
	public String getScaletoparenty() {
		return scaletoparenty;
	}
	public void setScaletoparenty(String scaletoparenty) {
		this.scaletoparenty = scaletoparenty;
	}
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
	public String getIfupdate() {
		return ifupdate;
	}
	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}
	
}
