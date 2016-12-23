package com.telezone.domain.classes;

public class MapList {
	private Integer recordid;

	private Integer mapid;

	private String mapinfo;

	private String mapdata;

	private Integer scale;

	private String mapname;

	private Integer maplevel;

	private Integer parentid;

	private float scaletoparentx;

	private float scaletoparenty;

	private Integer offsetx;

	private Integer offsety;

	private String compressdata;

	private Integer iscad;

	private Integer ifupdate;

	public String getCompressdata() {
		return compressdata;
	}

	public void setCompressdata(String compressdata) {
		this.compressdata = compressdata;
	}

	public Integer getIfupdate() {
		return ifupdate;
	}

	public void setIfupdate(Integer ifupdate) {
		this.ifupdate = ifupdate;
	}

	public Integer getIscad() {
		return iscad;
	}

	public void setIscad(Integer iscad) {
		this.iscad = iscad;
	}

	public String getMapdata() {
		return mapdata;
	}

	public void setMapdata(String mapdata) {
		this.mapdata = mapdata;
	}

	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public String getMapinfo() {
		return mapinfo;
	}

	public void setMapinfo(String mapinfo) {
		this.mapinfo = mapinfo;
	}

	public Integer getMaplevel() {
		return maplevel;
	}

	public void setMaplevel(Integer maplevel) {
		this.maplevel = maplevel;
	}

	public String getMapname() {
		return mapname;
	}

	public void setMapname(String mapname) {
		this.mapname = mapname;
	}

	public Integer getOffsetx() {
		return offsetx;
	}

	public void setOffsetx(Integer offsetx) {
		this.offsetx = offsetx;
	}

	public Integer getOffsety() {
		return offsety;
	}

	public void setOffsety(Integer offsety) {
		this.offsety = offsety;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public float getScaletoparentx() {
		return scaletoparentx;
	}

	public void setScaletoparentx(float scaletoparentx) {
		this.scaletoparentx = scaletoparentx;
	}

	public float getScaletoparenty() {
		return scaletoparenty;
	}

	public void setScaletoparenty(float scaletoparenty) {
		this.scaletoparenty = scaletoparenty;
	}

}
