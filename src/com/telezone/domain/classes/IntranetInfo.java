package com.telezone.domain.classes;

/**
 * 页面统计 Info
 * 
 * @author yeZhen
 * 
 * @date 2011-03
 * 
 */
public class IntranetInfo {
	private int countall;// 人员总数
	private int countwailai;// 外来人员
	private int countleader;// 矿高层人数
	private int countmiddleleader;// 矿机关人数
	private int countstaffer;// 矿基层人数
	private int countcar;// 车辆数
	private String flag;// 状态

	public int getCountall() {
		return countall;
	}

	public void setCountall(int countall) {
		this.countall = countall;
	}

	public int getCountwailai() {
		return countwailai;
	}

	public void setCountwailai(int countwailai) {
		this.countwailai = countwailai;
	}

	public int getCountleader() {
		return countleader;
	}

	public void setCountleader(int countleader) {
		this.countleader = countleader;
	}

	public int getCountmiddleleader() {
		return countmiddleleader;
	}

	public void setCountmiddleleader(int countmiddleleader) {
		this.countmiddleleader = countmiddleleader;
	}

	public int getCountstaffer() {
		return countstaffer;
	}

	public void setCountstaffer(int countstaffer) {
		this.countstaffer = countstaffer;
	}

	public int getCountcar() {
		return countcar;
	}

	public void setCountcar(int countcar) {
		this.countcar = countcar;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
