package com.kj133.entity.vo;

public class Outcar_life_reportVO {
	private String count; // 序号

	private String worktype; // 车辆类型

	private int cars; // 总车数

	private String name; // 车辆名称

	private int zaocheshu; // 早车数

	private int zaocishu; // 早次数

	private int zhongcheshu; // 中车数

	private int zhongcishu; // 中次数

	private int wancheshu; // 晚车数

	private int wancishu; // 晚次数

	private String zaoodds; // 早出勤率

	private String fenzhong;

	private String zhongodds; // 中出勤率

	private String wanodds; // 晚出勤率

	private String carodds; // 出车率
//	private String carodds;

	private String zaoct; // 早平均时长

	private String zhongct; // 中平均时长

	private String wanct; // 晚平均时长

	private int tote; // 合计车数

	private String alltime; // 总时长

	private String counttime; // 平均时长

	private String zaocheid; // 早车明细

	private String zhongcheid; // 中车明细

	private String wancheid; // 晚车明细

	private String cardid; // 车辆卡号

	private String zaotimes; // 早时长

	private String zhongtimes; // 中时长

	private String wantimes; // 晚时长

	private int zaoban; // 早车数

	private int zhongban; // 中车数

	private int wanban; // 晚车数

	private int cishu; // 班次数

	private int cheshu; // 班车数

	private String bancounttime; // 班平均时长

	private double banodds; // 班出勤率

	private String stafferid;

	private String banname;

	private String worktime;

	private String cidao;

	private String zaotui;

	public String getAlltime() {
		return alltime;
	}

	public void setAlltime(String alltime) {
		this.alltime = alltime;
	}

	public String getBanname() {
		return banname;
	}

	public void setBanname(String banname) {
		this.banname = banname;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}



	public int getCars() {
		return cars;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public String getCidao() {
		return cidao;
	}

	public void setCidao(String cidao) {
		this.cidao = cidao;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCounttime() {
		return counttime;
	}

	public void setCounttime(String counttime) {
		this.counttime = counttime;
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

	public int getTote() {
		return tote;
	}

	public void setTote(int tote) {
		this.tote = tote;
	}

	public int getWanban() {
		return wanban;
	}

	public void setWanban(int wanban) {
		this.wanban = wanban;
	}

	public String getWancheid() {
		return wancheid;
	}

	public void setWancheid(String wancheid) {
		this.wancheid = wancheid;
	}

	public int getWancheshu() {
		return wancheshu;
	}

	public void setWancheshu(int wancheshu) {
		this.wancheshu = wancheshu;
	}

	public int getWancishu() {
		return wancishu;
	}

	public void setWancishu(int wancishu) {
		this.wancishu = wancishu;
	}

	public String getWanct() {
		return wanct;
	}

	public void setWanct(String wanct) {
		this.wanct = wanct;
	}

	public String getWanodds() {
		return wanodds;
	}

	public void setWanodds(String wanodds) {
		this.wanodds = wanodds;
	}

	public String getWantimes() {
		return wantimes;
	}

	public void setWantimes(String wantimes) {
		this.wantimes = wantimes;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public int getZaoban() {
		return zaoban;
	}

	public void setZaoban(int zaoban) {
		this.zaoban = zaoban;
	}

	public String getZaocheid() {
		return zaocheid;
	}

	public void setZaocheid(String zaocheid) {
		this.zaocheid = zaocheid;
	}

	public int getZaocheshu() {
		return zaocheshu;
	}

	public void setZaocheshu(int zaocheshu) {
		this.zaocheshu = zaocheshu;
	}

	public int getZaocishu() {
		return zaocishu;
	}

	public void setZaocishu(int zaocishu) {
		this.zaocishu = zaocishu;
	}

	public String getZaoct() {
		return zaoct;
	}

	public void setZaoct(String zaoct) {
		this.zaoct = zaoct;
	}

	public String getZaoodds() {
		return zaoodds;
	}

	public void setZaoodds(String zaoodds) {
		this.zaoodds = zaoodds;
	}

	public String getZaotimes() {
		return zaotimes;
	}

	public void setZaotimes(String zaotimes) {
		this.zaotimes = zaotimes;
	}

	public String getZaotui() {
		return zaotui;
	}

	public void setZaotui(String zaotui) {
		this.zaotui = zaotui;
	}

	public int getZhongban() {
		return zhongban;
	}

	public void setZhongban(int zhongban) {
		this.zhongban = zhongban;
	}

	public String getZhongcheid() {
		return zhongcheid;
	}

	public void setZhongcheid(String zhongcheid) {
		this.zhongcheid = zhongcheid;
	}

	public int getZhongcheshu() {
		return zhongcheshu;
	}

	public void setZhongcheshu(int zhongcheshu) {
		this.zhongcheshu = zhongcheshu;
	}

	public int getZhongcishu() {
		return zhongcishu;
	}

	public void setZhongcishu(int zhongcishu) {
		this.zhongcishu = zhongcishu;
	}

	public String getZhongct() {
		return zhongct;
	}

	public void setZhongct(String zhongct) {
		this.zhongct = zhongct;
	}

	public String getZhongodds() {
		return zhongodds;
	}

	public void setZhongodds(String zhongodds) {
		this.zhongodds = zhongodds;
	}

	public String getZhongtimes() {
		return zhongtimes;
	}

	public void setZhongtimes(String zhongtimes) {
		this.zhongtimes = zhongtimes;
	}

	public String getBancounttime() {
		return bancounttime;
	}

	public void setBancounttime(String bancounttime) {
		this.bancounttime = bancounttime;
	}

	public double getBanodds() {
		return banodds;
	}

	public void setBanodds(double banodds) {
		this.banodds = banodds;
	}

	public int getCheshu() {
		return cheshu;
	}

	public void setCheshu(int cheshu) {
		this.cheshu = cheshu;
	}

	public int getCishu() {
		return cishu;
	}

	public void setCishu(int cishu) {
		this.cishu = cishu;
	}

	public String getFenzhong() {
		return fenzhong;
	}

	public void setFenzhong(String fenzhong) {
		this.fenzhong = fenzhong;
	}

	public String getCarodds() {
		return carodds;
	}

	public void setCarodds(String carodds) {
		this.carodds = carodds;
	}

//	public String getCarodds() {
//		return carodds;
//	}
//
//	public void setCarodds(String carodds) {
//		this.carodds = carodds;
//	}

}
