package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class Wordlib1 extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recordid;
    private String id_name;
	private String areaid;
	
	private String areatype;

	private String wordname;

	private String areaname;

	private String wordvalue;

	private String wordtype;

	private String valuetype;

	private String maxvalue;

	private String minvalue;

	private String maxlines;

	private String module;

	private String language;

	private String banname;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMaxlines() {
		return maxlines;
	}

	public void setMaxlines(String maxlines) {
		this.maxlines = maxlines;
	}

	public String getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}

	public String getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getValuetype() {
		return valuetype;
	}

	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}

	public String getWordname() {
		return wordname;
	}

	public void setWordname(String wordname) {
		this.wordname = wordname;
	}

	public String getWordtype() {
		return wordtype;
	}

	public void setWordtype(String wordtype) {
		this.wordtype = wordtype;
	}

	public String getWordvalue() {
		return wordvalue;
	}

	public void setWordvalue(String wordvalue) {
		this.wordvalue = wordvalue;
	}

	public String getBanname() {
		return banname;
	}

	public void setBanname(String banname) {
		this.banname = banname;
	}

	public String getAreatype() {
		return areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getId_name() {
		return id_name;
	}

	public void setId_name(String id_name) {
		this.id_name = id_name;
	}

}
