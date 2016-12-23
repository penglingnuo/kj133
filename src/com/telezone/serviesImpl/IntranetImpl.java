package com.telezone.serviesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.IntranetInfo;
import com.telezone.services.IIntranet;

public class IntranetImpl implements IIntranet {

	public Map<String, Object> getList() {
		OperateClass oc = new OperateClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<IntranetInfo> list = oc.selectCountList("IntranetInfo.selectCountList");
		returnMap.put("list", list);
		return returnMap;
	}

}
