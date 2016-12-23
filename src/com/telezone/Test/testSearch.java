package com.telezone.Test;

import java.util.HashMap;
import java.util.Map;

import com.telezone.services.IPersonInMine;
import com.telezone.serviesImpl.PersonInMineImpl;

import junit.framework.TestCase;

public class testSearch extends TestCase{
	public void testS() {
		IPersonInMine ird = new PersonInMineImpl();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		paramMap.put("content", "WD");
		
		returnMap = ird.search(paramMap);
	}
}
