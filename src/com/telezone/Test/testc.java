package com.telezone.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.Locator;
import com.telezone.services.IMapListChange;
import com.telezone.serviesImpl.MapListChangeImpl;

public class testc {
	public static void main(String[] args) {
		OperateClass oc = new OperateClass();
		
		IMapListChange imc = new MapListChangeImpl();
		Locator lr =  new Locator();
		lr.setMapid(new Integer("3"));
		lr.setState("正常");
		
		Map<String, Object> subStationParamMap = new HashMap<String, Object>();
		subStationParamMap.put("key", "2");
		subStationParamMap.put("Obj", lr);
		List subStationList = oc.selectCardReaderAndLocatorData("Locator.selectLocator", subStationParamMap);
		
		System.out.println("1243");
	}
}
