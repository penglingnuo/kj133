package com.telezone.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.telezone.serviesImpl.PersonInMineImpl;

public interface IPersonInMine {
	/**
	 * ��þ�����Ա������ֲ���Ϣ
	 * 
	 * @Date 2010-06-19
	 * 
	 * @param null
	 * 
	 * @author LYee
	 * 
	 */
	public Map<String, Object> getThePlaceOfPersonsInMine();

	/**
	 * ��þ�����Ա�����������Ϣ
	 * 
	 * @Date 2010-06-19
	 * 
	 * @param null
	 * 
	 * @author LYee
	 * 
	 */
	public Map<String, Object> getTheNumberOfPersonsInMine();

	/**
	 * ��õ�ǰ������Ա���ܺϼƵ���ϸ��Ա��Ϣ ����Map
	 * 
	 * @Date 2010-06-24
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> detailedData(String cardReaderId,
			String department);

	/**
	 * ��õ�ǰ������Ա���ܺϼƵ���ϸ��Ա��Ϣ��������λ���ͻ�վ������ID ����Map
	 * 
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> locationData();
	/**
	 * ��õ�ǰ������Ա���ܺϼƵ���ϸ��Ա��Ϣ��������λ���ͻ�վ������ID ����Map ��������
	 * 
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> locationDataByuserid(String userid);
	/**
	 * ����Ա������ ��õ�ǰ������Ա���ܺϼƵ���ϸ��Ա��Ϣ��������λ���ͻ�վ������ID ����Map ��������
	 * 
	 * @Date 2011-08-08
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> locationDataByuseridandstaffername(String userid,String staffername);
	/**
	 * �����û�ID�͵�ͼID�����Ա�ľ�����·��
	 * 
	 * @Date 2010-07-12
	 * 
	 * @author LYee
	 * 
	 * @param mapID����ͼID��
	 *            stafferID����ԱID
	 * 
	 * @return Map
	 */
	public Map<String, Object> getWayOfStaffer(String mapId, String cardID);

	/**
	 * ����
	 * 
	 * @Date 2010-07-12
	 * 
	 * @author LYee
	 * 
	 * @param mapID����ͼID��
	 *            stafferID����ԱID
	 * 
	 * @return Map
	 */
	public Map<String, Object> search(Map<String, Object> paramMap);

	/**
	 * ���ݿ��Ż�õ�ǰ������Ա�ľ�����Ϣ
	 * 
	 * @Date 2010-07-12
	 * 
	 * @author LYee
	 * 
	 * @param cardID����ID
	 * 
	 * @return Map
	 */
	public Map<String, Object> getWayOfStaffer(String cardID);

	/**
	 * ���Ҿ��³�ʱ��Ա����Ϣ
	 * 
	 * @Date 2010-07-08
	 * 
	 * @author LYee
	 * 
	 * @param null
	 * 
	 * @return Map
	 */
	public Map<String, Object> getOutOfTimeCard();
	
	/**
	 * ���Ҿ��³�ʱ��Ա����Ϣ
	 * 
	 * @Date 2010-07-08
	 * 
	 * @author LYee
	 * 
	 * @param null
	 * 
	 * @return Map
	 */
	public Map<String, Object> getStafferInCardReader(String id, String type, String cardReaderGround,String userid);
	
	/**
	 * ������ԱID�ţ��ҵ���Ա�ľ�����Ϣ
	 * ��Ҫ���ڵ�ͼ��Ա���ͼ��
	 * 
	 * @Date 2010-07-08
	 * 
	 * @author LYee
	 * 
	 * @param null
	 * 
	 * @return Map
	 */
	public Map<String, Object> getStafferInfo(String id);
	
	/**
	 * ��Ա����,Actionҳ���ʼ������ҵ����
	 * 
	 * @Date 2010-07-08
	 * 
	 * @author LYee
	 * 
	 * @param null
	 * 
	 * @return Map
	 */
	public Map<String, Object> search();
}
