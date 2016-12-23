package com.kj133.entity;

import java.sql.Blob;

import org.speedframework.entity.BaseObject;

public class Staffer extends BaseObject {

	private static final long serialVersionUID = 1L;

	private String recordid;

	private String stafferid;

	private String cardid;

	private String name;

	private String pinyin;

	private String sex;

	private String birthday;

	private String certificate;

	private String certificateid; 

	private String marriage; 

	private Integer stature; 

	private Integer weight; 

	private String bloodtype; 

	private String eyesight; 

	private String education; 

	private String technica; 

	private String workphone; 

	private String mobile; 

	private String address; 

	private String postzip; 

	private String homephone; 

	private String department; 

	private String group; 

	private String occupation; 

	private String worktype; 

	private String jointime; 

	private Blob photo; 

	private String createtime; 

	private String modifytime; 

	private String givewecard_time;

	private String givekjcard_time;

	private String we_cardid;

	private String dep_id;

	private String gztype;

	private String gzpwd;

	private String shunxu;
	private String ifother;
	private String iftemp;
	private String stafferinfo;
	private String workarea;
	private String cimportant_post;
	
	private String ifupdate;
	private String ifpublic;
	//ÐÂÔö×Ö¶Î
	private String pqdw;
	private String sid;
	private String isdaiban;
	private String minworktime;
	private String kuangdenghao;
	private String levelid;

	public String getShunxu() {
		return shunxu;
	}

	public void setShunxu(String shunxu) {
		this.shunxu = shunxu;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getCertificateid() {
		return certificateid;
	}

	public void setCertificateid(String certificateid) {
		this.certificateid = certificateid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDep_id() {
		return dep_id;
	}

	public void setDep_id(String dep_id) {
		this.dep_id = dep_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEyesight() {
		return eyesight;
	}

	public void setEyesight(String eyesight) {
		this.eyesight = eyesight;
	}

	public String getGroup() {
		return group;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getJointime() {
		return jointime;
	}

	public void setJointime(String jointime) {
		this.jointime = jointime;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPostzip() {
		return postzip;
	}

	public void setPostzip(String postzip) {
		this.postzip = postzip;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStafferid() {
		return stafferid;
	}

	public void setStafferid(String stafferid) {
		this.stafferid = stafferid;
	}

	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public String getTechnica() {
		return technica;
	}

	public void setTechnica(String technica) {
		this.technica = technica;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGivekjcard_time() {
		return givekjcard_time;
	}

	public void setGivekjcard_time(String givekjcard_time) {
		this.givekjcard_time = givekjcard_time;
	}

	public String getGivewecard_time() {
		return givewecard_time;
	}

	public void setGivewecard_time(String givewecard_time) {
		this.givewecard_time = givewecard_time;
	}

	public String getGzpwd() {
		return gzpwd;
	}

	public void setGzpwd(String gzpwd) {
		this.gzpwd = gzpwd;
	}

	public String getGztype() {
		return gztype;
	}

	public void setGztype(String gztype) {
		this.gztype = gztype;
	}

	public String getWe_cardid() {
		return we_cardid;
	}

	public void setWe_cardid(String we_cardid) {
		this.we_cardid = we_cardid;
	}

	public String getIfupdate() {
		return ifupdate;
	}

	public void setIfupdate(String ifupdate) {
		this.ifupdate = ifupdate;
	}

	public String getIfpublic() {
		return ifpublic;
	}

	public void setIfpublid(String ifpublic) {
		this.ifpublic = ifpublic;
	}

	public String getCimportant_post() {
		return cimportant_post;
	}

	public void setCimportant_post(String cimportant_post) {
		this.cimportant_post = cimportant_post;
	}

	public String getIfother() {
		return ifother;
	}

	public void setIfother(String ifother) {
		this.ifother = ifother;
	}

	public String getIftemp() {
		return iftemp;
	}

	public void setIftemp(String iftemp) {
		this.iftemp = iftemp;
	}

	public String getStafferinfo() {
		return stafferinfo;
	}

	public void setStafferinfo(String stafferinfo) {
		this.stafferinfo = stafferinfo;
	}

	public String getWorkarea() {
		return workarea;
	}

	public void setWorkarea(String workarea) {
		this.workarea = workarea;
	}

	public void setIfpublic(String ifpublic) {
		this.ifpublic = ifpublic;
	}

	public String getPqdw() {
		return pqdw;
	}

	public void setPqdw(String pqdw) {
		this.pqdw = pqdw;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getIsdaiban() {
		return isdaiban;
	}

	public void setIsdaiban(String isdaiban) {
		this.isdaiban = isdaiban;
	}

	public String getMinworktime() {
		return minworktime;
	}

	public void setMinworktime(String minworktime) {
		this.minworktime = minworktime;
	}

	public String getKuangdenghao() {
		return kuangdenghao;
	}

	public void setKuangdenghao(String kuangdenghao) {
		this.kuangdenghao = kuangdenghao;
	}

	public String getLevelid() {
		return levelid;
	}

	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}

}
