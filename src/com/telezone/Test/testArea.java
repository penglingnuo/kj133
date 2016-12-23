package com.telezone.Test;

import java.util.HashMap;
import java.util.Map;

import com.telezone.services.IArea;
import com.telezone.serviesImpl.AreaImpl;

import junit.framework.TestCase;

public class testArea extends TestCase{
	public void testArea() {
		IArea ia = new AreaImpl();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("areaName", "adsf");
		paramMap.put("areaType", "1");
		
		paramMap.put("areaMaxPerson", "1");
		paramMap.put("areaCaiJuePerson", "1");
		paramMap.put("areaOverTime", "1");
		paramMap.put("CareReaderTd", "5,6,7");
		paramMap.put("LocatorTd", "");
		
		ia.addArea(paramMap);
	}
}
