package com.kj133.entity.vo;

public class Car_life_reportVO {
	private String count; // 序号

	private String name; // 车辆名称

	private String cardid; // 车辆卡号

	private String worktype; // 车辆类型

	private int zaoban; // 早班数

	private int zhongban; // 中班数

	private int wanban; // 晚班数

	private String zaotimes; // 早时长
	
	private String fenzhong;// 早中晚分钟

	private String zhongtimes; // 中时长

	private String wantimes; // 晚时长

	private String alltime; // 总时长

	private String counttime; // 平均时长

	private int tote; // 合计班数

	private String stafferid;

	private String downtime;

	private String uptime;

	private String banname;

	private String worktime;

	private String zaocount;

	private String zhongcount;

	private String wancount;

	private String cidao;

	private String zaotui;

	private int downwellcount; // 入井次数

	// private String downwellcount; // 入井次数

	public String getAlltime() {
		return alltime;
	}

	public void setAlltime(String alltime) {
		this.alltime = alltime;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCounttime() {
		return counttime;
	}

	public void setCounttime(String counttime) {
		this.counttime = counttime;
	}

	/*
	 * public String getDownwellcount() { return downwellcount; }
	 * 
	 * public void setDownwellcount(String downwellcount) { this.downwellcount =
	 * downwellcount; }
	 */
	public String getBanname() {
		return banname;
	}

	public void setBanname(String banname) {
		this.banname = banname;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	// public String getWanban() {
	// return wanban;
	// }
	//
	// public void setWanban(String wanban) {
	// this.wanban = wanban;
	// }
	//
	// public String getZaoban() {
	// return zaoban;
	// }
	//
	// public void setZaoban(String zaoban) {
	// this.zaoban = zaoban;
	// }
	//
	// public String getZhongban() {
	// return zhongban;
	// }
	//
	// public void setZhongban(String zhongban) {
	// this.zhongban = zhongban;
	// }

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getWancount() {
		return wancount;
	}

	public void setWancount(String wancount) {
		this.wancount = wancount;
	}

	public String getZaocount() {
		return zaocount;
	}

	public void setZaocount(String zaocount) {
		this.zaocount = zaocount;
	}

	public String getZhongcount() {
		return zhongcount;
	}

	public void setZhongcount(String zhongcount) {
		this.zhongcount = zhongcount;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public String getCidao() {
		return cidao;
	}

	public void setCidao(String cidao) {
		this.cidao = cidao;
	}

	public String getZaotui() {
		return zaotui;
	}

	public void setZaotui(String zaotui) {
		this.zaotui = zaotui;
	}

	public int getDownwellcount() {
		return downwellcount;
	}

	public void setDownwellcount(int downwellcount) {
		this.downwellcount = downwellcount;
	}

	public int getWanban() {
		return wanban;
	}

	public void setWanban(int wanban) {
		this.wanban = wanban;
	}

	public int getZaoban() {
		return zaoban;
	}

	public void setZaoban(int zaoban) {
		this.zaoban = zaoban;
	}

	public int getZhongban() {
		return zhongban;
	}

	public void setZhongban(int zhongban) {
		this.zhongban = zhongban;
	}

	public int getTote() {
		return tote;
	}

	public void setTote(int tote) {
		this.tote = tote;
	}

	public String getWantimes() {
		return wantimes;
	}

	public void setWantimes(String wantimes) {
		this.wantimes = wantimes;
	}

	public String getZaotimes() {
		return zaotimes;
	}

	public void setZaotimes(String zaotimes) {
		this.zaotimes = zaotimes;
	}

	public String getZhongtimes() {
		return zhongtimes;
	}

	public void setZhongtimes(String zhongtimes) {
		this.zhongtimes = zhongtimes;
	}

	public String getFenzhong() {
		return fenzhong;
	}

	public void setFenzhong(String fenzhong) {
		this.fenzhong = fenzhong;
	}


}
