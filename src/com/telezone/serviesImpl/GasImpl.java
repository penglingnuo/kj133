package com.telezone.serviesImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.GasData;
import com.telezone.services.IGas;

public class GasImpl implements IGas {

	public List<GasData> getAlarmInfoOfGas() {
		OperateClass oc = new OperateClass();

		List<GasData> GasAlarmInfoList = oc
				.selectWithObjectString("GasData.selectGasData");

		return GasAlarmInfoList;
	}
}
