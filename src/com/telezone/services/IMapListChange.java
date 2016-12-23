package com.telezone.services;

import java.util.Map;

public interface IMapListChange {
	/**
	 * 地图下拉列表，包括定位器和基站的坐标ID 返回Map
	 * 
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	Map getCardReaderAndLocatorData(String mapId,String userid);
}
