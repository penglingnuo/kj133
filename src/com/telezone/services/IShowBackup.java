package com.telezone.services;

import java.util.Map;

public interface IShowBackup {

	/**
	 * 根据人员ID和时间获得当天的井下轨迹
	 * 
	 * @Date 2010 - 08 - 28
	 * 
	 * @author LYee
	 * 
	 * @return String
	 */
	Map<String, Object> searchHistoryByCardidDate(String keyWord, String date) throws Exception;
	/**
	 * 显示人员路径回放页面
	 * 
	 * @Date 2010 - 08 - 23
	 * 
	 * @author LYee
	 * 
	 * @return String
	 */
	Map<String, Object> search(String keyWord, String time) throws Exception;
}
