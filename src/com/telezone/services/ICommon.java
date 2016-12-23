package com.telezone.services;

import java.util.Map;

public interface ICommon {
	/**
	 * �����Ա��Ϣ
	 * 
	 * @Date 2010-08-23
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	Map<String, Object> staffer(String keyWord,String userid);
	/**
	 * ҳ����ط�վ�Ͷ�λ���ʼ��
	 * 
	 * @Date 2010-08-27
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	Map<String, Object> stafferAndLocator();
	/**
	 * �ֶ������Ա�������
	 * 
	 * @Date 2010-08-27
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	Map<String, Object> addWorkattendanceEx(Map<String, Object> parameterMap);
}
