package com.kj133.entity;

import org.speedframework.entity.BaseObject;

public class CardReaderhis  extends BaseObject{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String recordid;
	    private String cardreaderid;
	    private String crname;
	    private String shortname;
	    private String x;
	    private String y;
	    private String regdate;
	    private String mapid;
	    private String ignoretimes;
	    private String ignorelocator;
	    private String locatorignoretimes;
	    private String state;
	    private String modifydate;
	    private String ground;
	    private String statedate;
	    private String useantenna;
		public String getCardreaderid() {
			return cardreaderid;
		}
		public void setCardreaderid(String cardreaderid) {
			this.cardreaderid = cardreaderid;
		}
		public String getCrname() {
			return crname;
		}
		public void setCrname(String crname) {
			this.crname = crname;
		}
		public String getGround() {
			return ground;
		}
		public void setGround(String ground) {
			this.ground = ground;
		}
		public String getIgnorelocator() {
			return ignorelocator;
		}
		public void setIgnorelocator(String ignorelocator) {
			this.ignorelocator = ignorelocator;
		}
		public String getIgnoretimes() {
			return ignoretimes;
		}
		public void setIgnoretimes(String ignoretimes) {
			this.ignoretimes = ignoretimes;
		}
		public String getLocatorignoretimes() {
			return locatorignoretimes;
		}
		public void setLocatorignoretimes(String locatorignoretimes) {
			this.locatorignoretimes = locatorignoretimes;
		}
		public String getMapid() {
			return mapid;
		}
		public void setMapid(String mapid) {
			this.mapid = mapid;
		}
		public String getModifydate() {
			return modifydate;
		}
		public void setModifydate(String modifydate) {
			this.modifydate = modifydate;
		}
		public String getRecordid() {
			return recordid;
		}
		public void setRecordid(String recordid) {
			this.recordid = recordid;
		}
		public String getRegdate() {
			return regdate;
		}
		public void setRegdate(String regdate) {
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
		public String getStatedate() {
			return statedate;
		}
		public void setStatedate(String statedate) {
			this.statedate = statedate;
		}
		public String getUseantenna() {
			return useantenna;
		}
		public void setUseantenna(String useantenna) {
			this.useantenna = useantenna;
		}
		public String getX() {
			return x;
		}
		public void setX(String x) {
			this.x = x;
		}
		public String getY() {
			return y;
		}
		public void setY(String y) {
			this.y = y;
		}
}
