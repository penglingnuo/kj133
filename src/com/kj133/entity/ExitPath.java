package com.kj133.entity;

import org.speedframework.entity.BaseObject;
import java.sql.Blob;
public class ExitPath extends BaseObject{

	/**
	 * ”¶º±π‹¿Ì
	 */
	private static final long serialVersionUID = 1L;
	
	private String pathname;
	private String pathinfo;
	private Blob pathimage;
	private Blob compressdata;
	private String show;
	 
	public Blob getPathimage() {
		return pathimage;
	}
	public void setPathimage(Blob pathimage) {
		this.pathimage = pathimage;
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
	public Blob getCompressdata() {
		return compressdata;
	}
	public void setCompressdata(Blob compressdata) {
		this.compressdata = compressdata;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	
}
