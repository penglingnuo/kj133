package com.telezone.serviesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.EscapeLine;
import com.telezone.services.IEscapeLine;

public class EscapeLineImpl implements IEscapeLine {
	public Map<String, Object> saveEscapeLine(Map<String, Object> parameterMap) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		EscapeLine _el = new EscapeLine();
		_el.setPathname(parameterMap.get("name").toString().trim());
		
		List list = oc.selectWithObjectString("EscapeLine.selectEscapeLineByParameter", _el);
		
		String suc = "1";
		
		if(list.size() == 0) {
			EscapeLine el = new EscapeLine();
			el.setPathname(parameterMap.get("name").toString().trim());
			el.setPathinfo(parameterMap.get("info").toString().trim());
			el.setPoint(parameterMap.get("point").toString().trim());
			el.setMapid(parameterMap.get("mapid").toString().trim());
			el.setShow(parameterMap.get("isShow").toString().trim());
			
			suc = oc.insert("EscapeLine.insertEscapeLine", el);
		}else {
			suc = "-1";
		}
		
		returnMap.put("suc", suc);
		
		return returnMap;
	}

	public Map<String, Object> getLine(String name) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		EscapeLine el = new EscapeLine();
		el.setPathname(name.trim());
		
		List list = oc.selectWithObjectString("EscapeLine.selectEscapeLineByParameter", el);
		
		returnMap.put("list", list.get(0));
		
		return returnMap;
	}

	public Map<String, Object> modify(Map<String, Object> parameterMap) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		EscapeLine el = new EscapeLine();
		el.setOldname(parameterMap.get("oldname").toString().trim());
		el.setPathname(parameterMap.get("name").toString().trim());
		el.setPathinfo(parameterMap.get("info").toString().trim());
		el.setShow(parameterMap.get("isShow").toString().trim());
		
		EscapeLine _el = new EscapeLine();
		_el.setPathname(parameterMap.get("name").toString().trim());
		
		String suc = "1";
		
		if(el.getOldname().equals(el.getPathname())) {
			suc = oc.update("EscapeLine.updateEscapeLineWithSameName", el);
		}else if(!el.getOldname().equals(el.getPathname())){
			List list = oc.selectWithObjectString("EscapeLine.selectEscapeLineByParameter", _el);
			
			if(list.size() == 0) {
				suc = oc.update("EscapeLine.updateEscapeLine", el);
			}else {
				suc = "-1";
			}
		}
		
		returnMap.put("str", suc);
		
		return returnMap;
	}

	public Map<String, Object> getLineByMapid(String mapid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		EscapeLine el = new EscapeLine();
		el.setMapid(mapid);
		el.setShow("1");
		
		List list = oc.selectWithObjectString("EscapeLine.selectEscapeLineByParameter", el);
		
		returnMap.put("list", list);
		
		return returnMap;
	}
}
