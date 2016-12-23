package com.kj133.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.speedframework.entity.BaseObject;
public class BanType extends BaseObject{

	private static final long serialVersionUID = 1L;

	/**
	 * @param args∞‡¥Œ…Ë÷√
	 */
	private String ban_name;
	private String start_time;
	private String end_time;
	private String qidao_time;
	private String zaotui_time;
	private String start_stime;
	private String end_stime;
	private String start_xtime;
	private String end_xtime;
	private int add_time;
	private String dateoffset;
	private String ifupdate;
	private int ban_id;
	private int bantypeid;
	private String bd;
	private String lid;
 
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getBd() {
		return bd;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	public int getBan_id() {
		return ban_id;
	}
	public void setBan_id(int ban_id) {
		this.ban_id = ban_id;
	}
	public int getBantypeid() {
		return bantypeid;
	}
	public void setBantypeid(int bantypeid) {
		this.bantypeid = bantypeid;
	}
	public int getAdd_time() {
		return add_time;
	}
	public void setAdd_time(int add_time) {
		this.add_time = add_time;
	}
	public String getBan_name() {
		return ban_name;
	}
	public void setBan_name(String ban_name) {
		this.ban_name = ban_name;
	}
	public String getEnd_stime() {
		return end_stime;
	}
	public void setEnd_stime(String end_stime) {
		this.end_stime = end_stime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getEnd_xtime() {
		return end_xtime;
	}
	public void setEnd_xtime(String end_xtime) {
		this.end_xtime = end_xtime;
	}
	public String getQidao_time() {
		return qidao_time;
	}
	public void setQidao_time(String qidao_time) {
		this.qidao_time = qidao_time;
	}
	public String getStart_stime() {
		return start_stime;
	}
	public void setStart_stime(String start_stime) {
		this.start_stime = start_stime;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getStart_xtime() {
		return start_xtime;
	}
	public void setStart_xtime(String start_xtime) {
		this.start_xtime = start_xtime;
	}
	public String getZaotui_time() {
		return zaotui_time;
	}
	public void setZaotui_time(String zaotui_time) {
		this.zaotui_time = zaotui_time;
	}
	
    public String toString()
    {
      return ToStringBuilder.reflectionToString(this);	
    }
	public String getDateoffset() {
		return dateoffset;
	}
	public void setDateoffset(String dateoffset) {
		this.dateoffset = dateoffset;
	}
	public String getIfupdate() {
		return ifupdate;
	}
	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}
	 
 

}
