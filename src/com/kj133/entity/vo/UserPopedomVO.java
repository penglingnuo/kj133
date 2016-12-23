package com.kj133.entity.vo;

public class UserPopedomVO {

	  private String userid;
	  private String uid;
	  private String pid;
	  private String oname;
	  private String createtime;
	  private String phref;//权限连接
	  private String pname;//权限名字
	  private String pertain_to;//所属菜单
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public String getOname() {
			return oname;
		}
		public void setOname(String oname) {
			this.oname = oname;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getPname() {
			return pname;
		}
		public void setPname(String pname) {
			this.pname = pname;
		}
		public String getPertain_to() {
			return pertain_to;
		}
		public void setPertain_to(String pertain_to) {
			this.pertain_to = pertain_to;
		}
		public String getPhref() {
			return phref;
		}
		public void setPhref(String phref) {
			this.phref = phref;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
	 
}
