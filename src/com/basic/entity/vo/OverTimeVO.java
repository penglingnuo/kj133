package com.basic.entity.vo;

public class OverTimeVO {
	
	/**
	   * 下井超时
	   * */
		private String cardid;
		private String username;
		private String department;
		private String gro;
		private String info;
		private String downtime;
		private String uptime;
		private String staytime;
		private String overtime;
		
		
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getCardid() {
			return cardid;
		}
		public void setCardid(String cardid) {
			this.cardid = cardid;
		}
		public String getDowntime() {
			return downtime;
		}
		public void setDowntime(String downtime) {
			this.downtime = downtime;
		}
		public String getGro() {
			return gro;
		}
		public void setGro(String gro) {
			this.gro = gro;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getOvertime() {
			return overtime;
		}
		public void setOvertime(String overtime) {
			this.overtime = overtime;
		}
		public String getStaytime() {
			return staytime;
		}
		public void setStaytime(String staytime) {
			this.staytime = staytime;
		}
		public String getUptime() {
			return uptime;
		}
		public void setUptime(String uptime) {
			this.uptime = uptime;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}

}
