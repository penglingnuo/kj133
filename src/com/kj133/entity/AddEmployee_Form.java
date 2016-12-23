package com.kj133.entity;

 

import java.sql.Blob;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.upload.FormFile;

public class AddEmployee_Form {

	/**
	 * @param args
	 */
	    private FormFile file;
	    private String stafferid;//编号
		private String cardid;//卡号
		private String name;//姓名
		private String sex;//性别
		private String birthday;//生日
		private String certificate;//证件
		private String certificateid; //诤谏号码
		private String marriage; //婚姻状况
		private Integer stature; //身高
		private Integer weight; //体重
		private String bloodtype; //血型
		private String eyesight; //视力
		private String education; //文化程度
		private String technica;  //职称
		private String workphone; //电话
		private String mobile; //移动电话
		private String address; //住址
		private String postzip; //邮编
		private String homephone; //家庭电话
		private String department; //部门
		private String group; //组别
		private String occupation; //职务
		private String worktype; //工种
		private String jointime; //到职日期
		private String createtime; //创建时间
		private String modifytime; //修改时间
		private Blob photo; // 相片
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
		public FormFile getFile() {
			return file;
		}
		public void setFile(FormFile file) {
			this.file = file;
		}
		public String getGroup() {
			return group;
		}
		public void setGroup(String group) {
			this.group = group;
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
		public String getPostzip() {
			return postzip;
		}
		public void setPostzip(String postzip) {
			this.postzip = postzip;
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
		
		/**
		 * 输出表单信息
		 * */
		public String toString()
		{
			return ToStringBuilder.reflectionToString(this);
		}
		public String getDep_id() {
			return dep_id;
		}
		public void setDep_id(String dep_id) {
			this.dep_id = dep_id;
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
		public String getIfother() {
			return ifother;
		}
		public void setIfother(String ifother) {
			this.ifother = ifother;
		}
		public String getIfpublic() {
			return ifpublic;
		}
		public void setIfpublic(String ifpublic) {
			this.ifpublic = ifpublic;
		}
		public String getIftemp() {
			return iftemp;
		}
		public void setIftemp(String iftemp) {
			this.iftemp = iftemp;
		}
		public String getIfupdate() {
			return ifupdate;
		}
		public void setIfupdate(String ifupdate) {
			this.ifupdate = ifupdate;
		}
		public Blob getPhoto() {
			return photo;
		}
		public void setPhoto(Blob photo) {
			this.photo = photo;
		}
		public String getShunxu() {
			return shunxu;
		}
		public void setShunxu(String shunxu) {
			this.shunxu = shunxu;
		}
		public String getStafferinfo() {
			return stafferinfo;
		}
		public void setStafferinfo(String stafferinfo) {
			this.stafferinfo = stafferinfo;
		}
		public String getWe_cardid() {
			return we_cardid;
		}
		public void setWe_cardid(String we_cardid) {
			this.we_cardid = we_cardid;
		}
		public String getWorkarea() {
			return workarea;
		}
		public void setWorkarea(String workarea) {
			this.workarea = workarea;
		}
		public String getCimportant_post() {
			return cimportant_post;
		}
		public void setCimportant_post(String cimportant_post) {
			this.cimportant_post = cimportant_post;
		}

}
