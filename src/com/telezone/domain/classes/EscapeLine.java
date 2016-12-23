package com.telezone.domain.classes;

public class EscapeLine {
	private String oldname;

	private String pathname;

	private String pathinfo;

	private String point;

	private String mapid;

	private String show;

	public String getMapid() {
		return mapid;
	}

	public void setMapid(String mapid) {
		this.mapid = mapid.trim();
	}

	public String getPathinfo() {
		return pathinfo;
	}

	public void setPathinfo(String pathinfo) {
		this.pathinfo = pathinfo;
	}

	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
}
