package com.telezone.services;

import java.util.List;
import java.util.Map;

public interface ISetCoordinate {

	/**
	 * 
	 * 
	 * @Date 2010 - 08 - 15
	 * 
	 * @author LYee
	 * 
	 * @return String
	 */
	String saveXYOfCardreaderOrLocator(String type, String id, String x, String y, String mapid);
	
	/**
	 * 
	 * 
	 * @Date 2010 - 08 - 15
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	Map GetAllCoordinate();

	/**
	 * 
	 * @param cardid
	 * @return
	 */
	List GetCardReader(String cardid);
}
