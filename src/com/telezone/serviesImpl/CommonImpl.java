package com.telezone.serviesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.Locator;
import com.telezone.domain.classes.Staffer;
import com.telezone.domain.classes.WorkattendanceEx;
import com.telezone.services.ICommon;

public class CommonImpl implements ICommon {
	@SuppressWarnings("unchecked")
	public Map<String, Object> staffer(String keyWord,String userid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		Staffer s = new Staffer();
		s.setStafferid(keyWord);
		s.setCardId(keyWord);
		s.setPinyin(keyWord.toUpperCase());
		s.setName(keyWord);
		s.setUserid(userid);

		List<Staffer> stafferList = oc.selectWithObjectString(
				"Staffer.selectStaffer", s);

		returnMap.put("staffer", stafferList);

		return returnMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> stafferAndLocator() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();

		CardReader cr = new CardReader();
		List<CardReader> cardreaderList = oc.selectWithObjectString(
				"CardReader.selectCardReaderByParam", cr);

		Locator l = new Locator();
		List<Locator> locatorList = oc.selectWithObjectString(
				"Locator.selectLocator", l);

		returnMap.put("cardreaderList", cardreaderList);
		returnMap.put("locatorList", locatorList);

		return returnMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> addWorkattendanceEx(Map<String, Object> parameterMap) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		WorkattendanceEx we = new WorkattendanceEx();
		we.setCardid(parameterMap.get("cardid").toString());
		we.setStafferid(parameterMap.get("staffer").toString());
		we.setDowntime(parameterMap.get("downtime").toString());
		we.setUptime(parameterMap.get("uptime").toString());
		we.setDowncardreaderid(parameterMap.get("downcardreader").toString());
		we.setDownlocatorid(parameterMap.get("downlocator").toString());
		we.setUpcardreaderid(parameterMap.get("upcardreader").toString());
		we.setUplocatorid(parameterMap.get("uplocator").toString());
		
		oc.insert("Common.insertWorkattendanceEx", we);
		
		returnMap.put("flag", 1);
		
		return returnMap;
	}
}
