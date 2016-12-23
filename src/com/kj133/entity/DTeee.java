package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class DTeee extends BaseObject{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
   * ¶¯Ì¬Ê÷×´²Ëµ¥
   * */
	 private String id;
	 private String name;
	 private String pid;
	 private String link;
	 private String title;
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
