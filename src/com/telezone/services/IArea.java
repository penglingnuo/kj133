package com.telezone.services;

import java.util.List;
import java.util.Map;

import com.telezone.domain.classes.AreaInfo;

public interface IArea {
	/**
	 * ����һ������
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
	 * ����һ������ǰ���һ�����������Ƿ����
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
	 * ���ҳ���Ҳඨʱˢ�µ�������Ϣ
	 * 
	 * @param null
	 * 
	 * @Date 2010-07-20
	 * 
	 * @return List
	 */
	List<AreaInfo> areaInfoOfIndexRight();

	/**
	 * ����Ҳ��������Ϣʱ���ʵĵ�ַ����þ������Ա��Ϣ
	 * 
	 * @param ��վID�ַ�������λ��ID�ַ���
	 * 
	 * @Date 2010-07-20
	 * 
	 * @return List
	 */
	List areaDetail(String cardreaderid, String locatorid, String areaName);
}
