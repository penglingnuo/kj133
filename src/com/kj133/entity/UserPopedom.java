package com.kj133.entity;

/**
 *写一个构造函数来判断，如果是用户管理，那就
 *把构造函数添加到list里里面
 * */
public class UserPopedom {
    private String phref;
    private String pname;
	public String getPhref() {
		return phref;
	}
	public void setPhref(String phref) {
		this.phref = phref;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public UserPopedom(String phref, String pname) {
		super();
		this.phref = phref;
		this.pname = pname;
	}
}
