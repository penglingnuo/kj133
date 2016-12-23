package com.telezone.services;

import java.util.Map;

import com.telezone.domain.classes.EscapeLine;

public interface IEscapeLine {
	Map<String, Object> saveEscapeLine(Map<String, Object> parameterMap);
	Map<String, Object> getLine(String name);
	Map<String, Object> getLineByMapid(String mapid);
	Map<String, Object> modify(Map<String, Object> parameterMap);
}
