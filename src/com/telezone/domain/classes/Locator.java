package com.telezone.domain.classes;

import java.util.Date;
import java.util.List;

public class Locator implements java.io.Serializable {
	private static final long serialVersionUID = -6701907047345642002L;

	private Integer recordId;

	private Integer locatorId;

	private String lname;

	private String shortName;

	private Integer x;

	private Integer y;

	private Integer webx;

	private Integer weby;

	private String webxall;

	private String webyall;

	private Date regDate;

	private Integer mapid;

	private Date modifydate;

	private Short ground;

	private String state;

	private Integer ifUpdate;

	private Integer z;

	private Byte gasIgnore;

	private List personInLocator;

	private String personinminenumber;

	public Byte getGasIgnore() {
		return gasIgnore;
	}

	public void setGasIgnore(Byte gasIgnore) {
		this.gasIgnore = gasIgnore;
	}

	public Short getGround() {
		return ground;
	}

	public void setGround(Short ground) {
		this.ground = ground;
	}

	public Integer getIfUpdate() {
		return ifUpdate;
	}

	public void setIfUpdate(Integer ifUpdate) {
		this.ifUpdate = ifUpdate;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Integer getLocatorId() {
		return locatorId;
	}

	public void setLocatorId(Integer locatorId) {
		this.locatorId = locatorId;
	}

	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public List getPersonInLocator() {
		return personInLocator;
	}

	public void setPersonInLocator(List personInLocator) {
		this.personInLocator = personInLocator;
	}

	public String getPersoninminenumber() {
		return personinminenumber;
	}

	public void setPersoninminenumber(String personinminenumber) {
		this.personinminenumber = personinminenumber;
	}

	public Integer getWebx() {
		return webx;
	}

	public void setWebx(Integer webx) {
		this.webx = webx;
	}

	public Integer getWeby() {
		return weby;
	}

	public void setWeby(Integer weby) {
		this.weby = weby;
	}

	public String getWebxall() {
		return webxall;
	}

	public String getWebyall() {
		return webyall;
	}

	public void setWebxall(String webxall) {
		this.webxall = webxall;
	}

	public void setWebyall(String webyall) {
		this.webyall = webyall;
	}
}