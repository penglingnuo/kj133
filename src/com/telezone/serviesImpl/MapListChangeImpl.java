package com.telezone.serviesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.AlarmInfoObject;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.Locator;
import com.telezone.domain.classes.MapList;
import com.telezone.domain.classes.PersonInMine;
import com.telezone.services.IMapListChange;

public class MapListChangeImpl implements IMapListChange {
	public Map<String, Object> getCardReaderAndLocatorData(String mapId,String userid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		MapList ml = new MapList();
		ml.setMapid(Integer.parseInt(mapId));
		
		List mapList = oc.selectWithObjectString("MapList.selectAllDataByParameter", ml);
		
		MapList _ml = (MapList) mapList.get(0);
		
		// 获得分站信息
		CardReader cr = new CardReader();
		if(_ml.getMaplevel().intValue() != 0){
			cr.setMapid(new Integer(mapId));
		}
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
					"ReaderState611.selectCardReaderByState", _cr);

			// 基站信号中断
			if(cardReaderState.size() > 0) {
				AlarmInfoObject aio = (AlarmInfoObject) cardReaderState.get(0);

				if (aio.getAlarmInfo().toString().trim().indexOf("中断") > 0) {
					_cr.setCardreaderstate("1");
				}else if(aio.getAlarmInfo().toString().trim().indexOf("报警") > 0) {
					_cr.setCardreaderstate("2");
				} else {
					_cr.setCardreaderstate("0");
				}
			}else {
				_cr.setCardreaderstate("0");
			}
			

			PersonInMine pim = new PersonInMine();
			pim.setCardreaderid(_cr.getCardreaderid());
			pim.setUserid(userid);
			List<PersonInMine> personInMineList = new ArrayList<PersonInMine>();
			
			// 获得基站下人员
			if(_cr.getGround().intValue() == 0) {//井下分站
				personInMineList = oc.selectWithObjectString(
						"PersonInMineDetailInfo.selectLocationDataForMap", pim);
			}else if(_cr.getGround().intValue() == 1) {//井上分站
				personInMineList = oc.selectWithObjectString(
						"PersonInMineDetailInfo.selectLocationDataForMapUp", pim);
			}
			PersonInMine _p=null;
			if(personInMineList!=null){
				_p = personInMineList.get(0);
			}
			if(_p!=null){
				//各个分站的具体认输
				_cr.setReaderCode(_p.getPersonincardreadernumber());
			}
		}

		// 定位器信息
		Locator lr = new Locator();
		if(_ml.getMaplevel().intValue() != 0){
			lr.setMapid(new Integer(mapId));
		}
		lr.setState("正常");

		subStationParamMap = new HashMap<String, Object>();
		subStationParamMap.put("key", "2");
		subStationParamMap.put("Obj", lr);
		List locatorList = oc.selectCardReaderAndLocatorData(
				"Locator.selectLocator", subStationParamMap);

		// 获得定位器下人员
		for (int i = 0; i < locatorList.size(); i++) {
			Locator _l = (Locator) locatorList.get(i);
			PersonInMine pim = new PersonInMine();
			pim.setLid(Integer.toString(_l.getLocatorId()));

			List<PersonInMine> personInMineList = oc.selectWithObjectString(
					"PersonInMineDetailInfo.selectLocationDataForMap", pim);
					
			PersonInMine _p = personInMineList.get(0);
			_l.setPersoninminenumber(_p.getPersonincardreadernumber());
		}

		returnMap.put("CardReader", subStationList);
		returnMap.put("LocatorData", locatorList);
		returnMap.put("MapList", _ml);

		//获得井下人员总数
		List allStaffers = oc.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMine");
		returnMap.put("AllStaffers", allStaffers.size());
		
		return returnMap;
	}
}
