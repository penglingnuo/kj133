package com.telezone.serviesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.AreaDetail;
import com.telezone.domain.classes.AreaInfo;
import com.telezone.domain.classes.PersonInMine;
import com.telezone.domain.classes.PersonInMineDetailInfo;
import com.telezone.services.IArea;

public class AreaImpl implements IArea {
	public Map<String, Object> addArea(Map<String, Object> paramMap) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();

		AreaInfo ai = new AreaInfo();
		AreaDetail ad = new AreaDetail();

		ai.setAreaname(paramMap.get("areaName").toString().trim());

		String areaType = paramMap.get("areaType").toString().trim();

		if (areaType.equals("1")) {
			areaType = "重点区域";
		} else if (areaType.equals("2")) {
			areaType = "采掘区域";
		} else if (areaType.equals("3")) {
			areaType = "禁入区域";
		}

		List _l = oc.selectWithObjectString("AreaInfo.selectMaxId");

		if (((AreaInfo) _l.get(0)).getAreaid() == null) {
			ai.setAreaid("1");
		} else {
			ai.setAreaid(((AreaInfo) _l.get(0)).getAreaid());
		}

		ai.setAreatype(areaType);
		ai.setAreaorder("一级区域");
		ai.setMaxsum(paramMap.get("areaMaxPerson").toString().trim());
		ai.setCaijuesum(paramMap.get("areaCaiJuePerson").toString().trim());
		ai.setAreainfo("  ");
		ai.setStayminute(paramMap.get("areaOverTime").toString().trim());

		String returnStr = oc.insert("AreaInfo.addArea", ai);

		if (returnStr.equals("1")) {// 插入成功
			if (paramMap.get("CareReaderTd").toString().trim().length() > 0) {
				List list = Arrays.asList(paramMap.get("CareReaderTd")
						.toString().trim().split(","));

				ad.setAreaname(paramMap.get("areaName").toString().trim());
				ad.setType("分站");

				for (int i = 0; i < list.size(); i++) {
					ad.setPointid(Integer.parseInt(list.get(i).toString()));

					oc.insert("AreaDetail.addAreaDetail", ad);
				}
			}
			if (paramMap.get("LocatorTd").toString().trim().length() > 0) {
				List list = Arrays.asList(paramMap.get("LocatorTd").toString()
						.trim().split(","));

				ad.setAreaname(paramMap.get("areaName").toString().trim());
				ad.setType("定位器");

				for (int i = 0; i < list.size(); i++) {
					ad.setPointid(Integer.parseInt(list.get(i).toString()));

					oc.insert("AreaDetail.addAreaDetail", ad);
				}
			}
		}
		returnMap.put("returnStr", returnStr);

		return returnMap;
	}

	public Map<String, Object> checkArea(Map<String, Object> paramMap) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();

		AreaInfo ai = new AreaInfo();
		ai.setAreaname(paramMap.get("areaName").toString().trim());

		List<AreaInfo> list = oc.selectWithObjectString(
				"AreaInfo.checkAreaByParameter", ai);

		String flag = "";
		if (list.size() > 0) {
			flag = "区域名称重复";
		}

		// 检查基站是否重复
		List careReaderTdList = Arrays.asList(paramMap.get("CareReaderTd")
				.toString().trim().split(","));
		List locatorTdList = Arrays.asList(paramMap.get("LocatorTd").toString()
				.trim().split(","));

		ai.setAreaname(paramMap.get("areaName").toString().trim());
		ai.setAreadetailtype("分站");
		ai.setPointlist(careReaderTdList);

		List<AreaDetail> CareReaderTdList = oc.selectWithObjectString(
				"AreaDetail.selectAreaDetailByList", ai);

		if (flag.length() > 0) {
			if (CareReaderTdList.size() > 0) {
				flag += "; 含有重复的分站";
			}
		} else {
			if (CareReaderTdList.size() > 0) {
				flag += "含有重复的分站";
			}
		}

		ai.setAreadetailtype("定位器");
		ai.setPointlist(locatorTdList);

		List<AreaDetail> LocatorTdList = oc.selectWithObjectString(
				"AreaDetail.selectAreaDetailByList", ai);

		if (flag.length() > 0) {
			if (LocatorTdList.size() > 0) {
				flag += "; 含有重复的定位器";
			}
		} else {
			if (LocatorTdList.size() > 0) {
				flag += "含有重复的定位器";
			}
		}

		returnMap.put("CheckResult", flag);

		return returnMap;
	}

	public List<AreaInfo> areaInfoOfIndexRight() {
		OperateClass oc = new OperateClass();
		// 获得所有的区域
		List<AreaInfo> areaInfoList = oc
				.selectWithObjectString("AreaInfo.selectAllArea");
		// 获得该区域下的基站和定位器数
		for (AreaInfo _ai : areaInfoList) {
			AreaInfo _ai1 = new AreaInfo();
			_ai1.setAreaname(_ai.getAreaname());

			List<AreaDetail> stationList = oc.selectWithObjectString(
					"AreaDetail.selectAreaDetailByParameter", _ai1);

			_ai.setCardreaderAndLocator(stationList);

			List<PersonInMine> personInMineList = oc
					.selectWithObjectString("PersonInMineDetailInfo.selectCardInCardReaderCount");

			for (AreaDetail ad : _ai.getCardreaderAndLocator()) {
				for (PersonInMine pim : personInMineList) {
					if (ad.getPointid().intValue() == pim.getCardreaderid()
							.intValue()) {
						int i = _ai.getStafferOfCardreaderAndLocator();
						i = i + Integer.parseInt(pim.getNumber());
						_ai.setStafferOfCardreaderAndLocator(i);
					}
				}
			}
		}

		return areaInfoList;
	}

	public List areaDetail(String cardreaderid, String locatorid,
			String areaName) {
		OperateClass oc = new OperateClass();

		List cardreaderidList = Arrays.asList(cardreaderid.split(","));
		List locatoridList = Arrays.asList(locatorid.split(","));

		List returnList = new ArrayList();

		for (int i = 0; i < cardreaderidList.size(); i++) {
			PersonInMineDetailInfo pidi = new PersonInMineDetailInfo();
			pidi.setCardreader(cardreaderidList.get(i).toString().trim());
			pidi.setAreaname(areaName);

			List<PersonInMine> personInMineList = oc.selectWithObjectString(
					"PersonInMineDetailInfo.selectPersonsInMineByCardreaderId",
					pidi);

			returnList.addAll(personInMineList);
		}

		return returnList;
	}
}
