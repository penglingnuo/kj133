package com.kj133.entity;

/**
 *дһ�����캯�����жϣ�������û������Ǿ�
 *�ѹ��캯����ӵ�list������
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
