package com.kj133.entity;

import org.apache.struts.upload.FormFile;
import org.speedframework.entity.BaseObject;

public class MapAdd extends BaseObject{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
    * Ôö¼ÓµØÍ¼
    * */
	private FormFile file;
    private String fname;
    private String size;
    private String name;
    private String scale;
    private String info;
    private String id;
    private String pho;
	public String getPho() {
		return pho;
	}
	public void setPho(String pho) {
		this.pho = pho;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
   
}
