package com.telezone.services;

import java.util.List;
import java.util.Map;

import com.telezone.domain.classes.GasData;

public interface IGas {
	/**
	 * �����˹��������
	 * 
	 * @Date 2010-06-07
	 * 
	 * @author LYee
	 * 
	 * @return List<GasData>
	 */
	public List<GasData> getAlarmInfoOfGas();
}
