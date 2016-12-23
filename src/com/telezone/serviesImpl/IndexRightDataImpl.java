package com.telezone.serviesImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.AlarmInfoObject;
import com.telezone.domain.classes.AreaDetail;
import com.telezone.domain.classes.AreaInfo;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.PersonInMine;
import com.telezone.services.IIndexRightData;

public class IndexRightDataImpl implements IIndexRightData {

	private final Logger logger = Logger.getLogger(this.getClass());

//	static {
//		OperateClass oc = new OperateClass();
//		//获得分站中断信息
//		Connection conn = oc.getCurrentConnection();
//		try {
//			CallableStatement cstmt = conn
//					.prepareCall("{call dbo.Calreaderhalt()}");
//			cstmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	/*
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return 返回地图列表List
	 * 
	 */
	public List mapList() {
		OperateClass oc = new OperateClass();
		List list = oc.selectWithObjectString("MapList.selectAllData");
		return list;
	}

	/*
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return 返回报警信息List
	 */
	public List alarmInfoList() {
		OperateClass oc = new OperateClass();
		List list = oc.selectWithObjectString("AlarmInfo.selectAlarmInfo");
		return list;
	}

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
	public Map<String, Object> alarmInfoMap() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		//分站报警信息
		List list = new ArrayList();
		
		CardReader cr = new CardReader();
		cr.setState("正常");

		Map<String, Object> subStationParamMap = new HashMap<String, Object>();
		subStationParamMap.put("key", "1");
		subStationParamMap.put("Obj", cr);
		List subStationList = oc.selectCardReaderAndLocatorData(
				"CardReader.selectCardReaderByParam", subStationParamMap);

		for (int i = 0; i < subStationList.size(); i++) {
			CardReader _cr = (CardReader) subStationList.get(i);
			// 获得基站状态
			List<AlarmInfoObject> cardReaderState = oc.selectWithObjectString(
					"ReaderState611.selectCardReaderByStateAlarm", _cr);

			// 基站出问题
			if(cardReaderState.size() > 0) {
				list.add(cardReaderState.get(0));
			}
		}

		// 获得报警信息的数据，然后关联过的所有信息
		returnMap.put("SubStationAlarmInfo", list);

		// 获得卡信息
		List CardBetty = new ArrayList();
		CardBetty = oc.selectWithObjectString("CardBetty.selectCardBetty");

		// 获得报警信息的数据，然后关联过的所有信息
		returnMap.put("CardBettyAlarmInfo", CardBetty);

		// 井下人员超时
		PersonInMine pim = new PersonInMine();
		pim.setCrname("井下超时");
		List OverTimeCardList = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectOverTimeCard", pim);
		returnMap.put("OverTime", OverTimeCardList);

		List<AreaInfo> areaInfoList = oc
				.selectWithObjectString("AreaInfo.selectAllArea");

		// 区域超员、区域超时、区域禁入报警一块处理
		List<AreaInfo> overMaxPerson = new ArrayList<AreaInfo>();
		List<AreaInfo> overTime = new ArrayList<AreaInfo>();
		List<AreaInfo> stopArea = new ArrayList<AreaInfo>();

		// 首先获得本区域下的所有卡信息
		for (AreaInfo _ai : areaInfoList) {
			// 获得该区域下的所有人员
			List stafferInAreaList = oc.selectWithObjectString(
					"Area.selectStafferInAreaByAreaId", _ai);

			// 区域禁入
			if (_ai.getAreatype().contains("禁入")) {
				if (stafferInAreaList.size() > 0) {
					_ai.setStafferinarea(stafferInAreaList);
					stopArea.add(_ai);
				}
			} else {
				// 区域超员
				if (Integer.parseInt(_ai.getMaxsum()) < stafferInAreaList
						.size()) {
					_ai.setStafferinarea(stafferInAreaList);
					overMaxPerson.add(_ai);
				}
			}
		}

		returnMap.put("overMaxPerson", overMaxPerson);
		returnMap.put("stopArea", stopArea);

		//井下超员
		pim.setCrname("井下超员");
		List _list = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectOverNumber", pim);
		
		int maxNumber = ((PersonInMine)_list.get(0)).getCardid().intValue();
		
		List _l = oc.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMine");
		
		if(maxNumber < _l.size()) {
			returnMap.put("overMaxPersonInMine", _l.size() - maxNumber);
		}else {
			returnMap.put("overMaxPersonInMine", "0");
		}
		
		//定位器报警
		List locatorAlarmList = oc.selectWithObjectString("Locator.selectAlarmLocator");
		returnMap.put("LocatorAlarm", locatorAlarmList);
		
		return returnMap;
	}
}
