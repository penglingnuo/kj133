package com.telezone.services;

import java.util.List;
import java.util.Map;

import com.telezone.domain.classes.AreaInfo;

public interface IArea {
	/**
	 * 增加一个区域
	 * 
	 * @param paramMap
	 * 
	 * @return Map
	 * 
	 * @Date 2010-07-19
	 * 
	 * @author LYee
	 */
	Map<String, Object> addArea(Map<String, Object> paramMap);

	/**
	 * 增加一个区域前检查一下区域名称是否存在
	 * 
	 * @param paramMap
	 * 
	 * @return Map
	 * 
	 * @Date 2010-07-20
	 * 
	 * @author LYee
	 */
	Map<String, Object> checkArea(Map<String, Object> paramMap);

	/**
	 * 获得页面右侧定时刷新的区域信息
	 * 
	 * @param null
	 * 
	 * @Date 2010-07-20
	 * 
	 * @return List
	 */
	List<AreaInfo> areaInfoOfIndexRight();

	/**
	 * 点击右侧的区域信息时访问的地址，获得具体的人员信息
	 * 
	 * @param 分站ID字符串，定位器ID字符串
	 * 
	 * @Date 2010-07-20
	 * 
	 * @return List
	 */
	List areaDetail(String cardreaderid, String locatorid, String areaName);
}
