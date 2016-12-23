package com.kj133.entity;

import org.apache.struts.upload.FormFile;
import org.speedframework.entity.BaseObject;

public class EmergencyAdd extends BaseObject{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
    * Ôö¼ÓµØÍ¼
    * */
	private FormFile file;

    private String name;

    private String info;
    private String sid;


	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
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
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

   
}
