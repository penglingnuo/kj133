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
	 * 获得井下人员的区域分布信息
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
	 * 获得井下人员的区域汇总信息
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
	 * 获得当前井下人员汇总合计的明细人员信息 返回Map
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
	 * 获得当前井下人员汇总合计的明细人员信息，包括定位器和基站的坐标ID 返回Map
	 * 
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> locationData();
	/**
	 * 获得当前井下人员汇总合计的明细人员信息，包括定位器和基站的坐标ID 返回Map 新增方法
	 * 
	 * @Date 2010-06-29
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> locationDataByuserid(String userid);
	/**
	 * 根据员工姓名 获得当前井下人员汇总合计的明细人员信息，包括定位器和基站的坐标ID 返回Map 新增方法
	 * 
	 * @Date 2011-08-08
	 * 
	 * @author LYee
	 * 
	 * @return Map
	 */
	public Map<String, Object> locationDataByuseridandstaffername(String userid,String staffername);
	/**
	 * 根据用户ID和地图ID获得人员的经过的路径
	 * 
	 * @Date 2010-07-12
	 * 
	 * @author LYee
	 * 
	 * @param mapID：地图ID；
	 *            stafferID：人员ID
	 * 
	 * @return Map
	 */
	public Map<String, Object> getWayOfStaffer(String mapId, String cardID);

	/**
	 * 搜索
	 * 
	 * @Date 2010-07-12
	 * 
	 * @author LYee
	 * 
	 * @param mapID：地图ID；
	 *            stafferID：人员ID
	 * 
	 * @return Map
	 */
	public Map<String, Object> search(Map<String, Object> paramMap);

	/**
	 * 根据卡号获得当前井下人员的具体信息
	 * 
	 * @Date 2010-07-12
	 * 
	 * @author LYee
	 * 
	 * @param cardID：卡ID
	 * 
	 * @return Map
	 */
	public Map<String, Object> getWayOfStaffer(String cardID);

	/**
	 * 查找井下超时人员的信息
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
	 * 查找井下超时人员的信息
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
	 * 根据人员ID号，找到人员的具体信息
	 * 主要用于地图人员点击图标
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
	 * 人员搜索,Action页面初始化调用业务类
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
