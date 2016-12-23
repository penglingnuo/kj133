package com.telezone.domain.classes;

import java.util.Date;
import java.util.List;

public class CardReader implements java.io.Serializable {
	private Integer recordid;

	private Integer cardreaderid;

	private String crname;

	private String shortname;

	private Integer x;
	
	private Integer y;

	private String webx;

	private String weby;

	private String webxall;

	private String webyall;

	private Date regdate;

	private Integer mapid;

	private Integer ignoretimes;

	private Integer ignorelocator;

	private Integer locatorignoretimes;

	private String state;

	private Date modifydate;

	private Integer ground;

	private Integer checkReader;

	private Short useantenna;

	private Integer ifAlarm;

	private Integer ifUpdate;

	private String readerCode;

	private Integer z;

	private Byte gasIgnore;

	private List personInCardReader;

	private String cardreaderstate;// 用于判断当前基站的状态，中断，禁入，报警等等。0：正常；1：信号中断；2：报警

	private String userid;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getCardreaderid() {
		return cardreaderid;
	}

	public void setCardreaderid(Integer cardreaderid) {
		this.cardreaderid = cardreaderid;
	}

	public Integer getCheckReader() {
		return checkReader;
	}

	public void setCheckReader(Integer checkReader) {
		this.checkReader = checkReader;
	}

	public String getCrname() {
		return crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}

	public Byte getGasIgnore() {
		return gasIgnore;
	}

	public void setGasIgnore(Byte gasIgnore) {
		this.gasIgnore = gasIgnore;
	}

	public Integer getGround() {
		return ground;
	}

	public void setGround(Integer ground) {
		this.ground = ground;
	}

	public Integer getIfAlarm() {
		return ifAlarm;
	}

	public void setIfAlarm(Integer ifAlarm) {
		this.ifAlarm = ifAlarm;
	}

	public Integer getIfUpdate() {
		return ifUpdate;
	}

	public void setIfUpdate(Integer ifUpdate) {
		this.ifUpdate = ifUpdate;
	}

	public Integer getIgnorelocator() {
		return ignorelocator;
	}

	public void setIgnorelocator(Integer ignorelocator) {
		this.ignorelocator = ignorelocator;
	}

	public Integer getIgnoretimes() {
		return ignoretimes;
	}

	public void setIgnoretimes(Integer ignoretimes) {
		this.ignoretimes = ignoretimes;
	}

	public Integer getLocatorignoretimes() {
		return locatorignoretimes;
	}

	public void setLocatorignoretimes(Integer locatorignoretimes) {
		this.locatorignoretimes = locatorignoretimes;
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

	public String getReaderCode() {
		return readerCode;
	}

	public void setReaderCode(String readerCode) {
		this.readerCode = readerCode;
	}

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Short getUseantenna() {
		return useantenna;
	}

	public void setUseantenna(Short useantenna) {
		this.useantenna = useantenna;
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

	public List getPersonInCardReader() {
		return personInCardReader;
	}

	public void setPersonInCardReader(List personInCardReader) {
		this.personInCardReader = personInCardReader;
	}

	public String getCardreaderstate() {
		return cardreaderstate;
	}

	public void setCardreaderstate(String cardreaderstate) {
		this.cardreaderstate = cardreaderstate;
	}

	public String getWebx() {
		return webx;
	}

	public void setWebx(String webx) {
		this.webx = webx;
	}

	public String getWeby() {
		return weby;
	}

	public void setWeby(String weby) {
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