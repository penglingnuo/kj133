package com.telezone.services;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 * 
 * 
 */
public interface IIndexRightData {

	/*
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return 返回报警信息String
	 */
	public List alarmInfoList();

	/*
	 * 获得页面右侧的报警信息
	 * 
	 * 第二次修改时间：2010-07-07
	 * 
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return 返回报警信息Map
	 */
	public Map<String, Object> alarmInfoMap();

	/*
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return 返回报警信息String
	 */
	public List mapList();
}
