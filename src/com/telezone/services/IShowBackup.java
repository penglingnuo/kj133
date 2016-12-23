package com.telezone.services;

import java.util.Map;

public interface IShowBackup {

	/**
	 * ������ԱID��ʱ���õ���ľ��¹켣
	 * 
	 * @Date 2010 - 08 - 28
	 * 
	 * @author LYee
	 * 
	 * @return String
	 */
	Map<String, Object> searchHistoryByCardidDate(String keyWord, String date) throws Exception;
	/**
	 * ��ʾ��Ա·���ط�ҳ��
	 * 
	 * @Date 2010 - 08 - 23
	 * 
	 * @author LYee
	 * 
	 * @return String
	 */
	Map<String, Object> search(String keyWord, String time) throws Exception;
}
