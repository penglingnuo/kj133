package com.telezone.domain.classes;

import java.util.List;

public class AreaInfo {
	private String areaid;

	private String areaname;

	private String areaorder;

	private String parentarea;

	private String areatype;

	private String maxsum;

	private String caijuesum;

	private String stayminute;

	private String areainfo;

	private List<AreaDetail> CardreaderAndLocator;

	private int stafferOfCardreaderAndLocator = 0;

	private List pointlist;

	private String areadetailtype;

	private List stafferinarea;

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAreainfo() {
		return areainfo;
	}

	public void setAreainfo(String areainfo) {
		this.areainfo = areainfo;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreaorder() {
		return areaorder;
	}

	public void setAreaorder(String areaorder) {
		this.areaorder = areaorder;
	}

	public String getAreatype() {
		return areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}

	public String getCaijuesum() {
		return caijuesum;
	}

	public void setCaijuesum(String caijuesum) {
		this.caijuesum = caijuesum;
	}

	public String getMaxsum() {
		return maxsum;
	}

	public void setMaxsum(String maxsum) {
		this.maxsum = maxsum;
	}

	public String getParentarea() {
		return parentarea;
	}

	public void setParentarea(String parentarea) {
		this.parentarea = parentarea;
	}

	public String getStayminute() {
		return stayminute;
	}

	public void setStayminute(String stayminute) {
		this.stayminute = stayminute;
	}

	public List<AreaDetail> getCardreaderAndLocator() {
		return CardreaderAndLocator;
	}

	public void setCardreaderAndLocator(List<AreaDetail> cardreaderAndLocator) {
		CardreaderAndLocator = cardreaderAndLocator;
	}

	public int getStafferOfCardreaderAndLocator() {
		return stafferOfCardreaderAndLocator;
	}

	public void setStafferOfCardreaderAndLocator(
			int stafferOfCardreaderAndLocator) {
		this.stafferOfCardreaderAndLocator = stafferOfCardreaderAndLocator;
	}

	public String getAreadetailtype() {
		return areadetailtype;
	}

	public void setAreadetailtype(String areadetailtype) {
		this.areadetailtype = areadetailtype;
	}

	public List getPointlist() {
		return pointlist;
	}

	public void setPointlist(List pointlist) {
		this.pointlist = pointlist;
	}

	public List getStafferinarea() {
		return stafferinarea;
	}

	public void setStafferinarea(List stafferinarea) {
		this.stafferinarea = stafferinarea;
	}

}
