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
			areaType = "�ص�����";
		} else if (areaType.equals("2")) {
			areaType = "�ɾ�����";
		} else if (areaType.equals("3")) {
			areaType = "��������";
		}

		List _l = oc.selectWithObjectString("AreaInfo.selectMaxId");

		if (((AreaInfo) _l.get(0)).getAreaid() == null) {
			ai.setAreaid("1");
		} else {
			ai.setAreaid(((AreaInfo) _l.get(0)).getAreaid());
		}

		ai.setAreatype(areaType);
		ai.setAreaorder("һ������");
		ai.setMaxsum(paramMap.get("areaMaxPerson").toString().trim());
		ai.setCaijuesum(paramMap.get("areaCaiJuePerson").toString().trim());
		ai.setAreainfo("  ");
		ai.setStayminute(paramMap.get("areaOverTime").toString().trim());

		String returnStr = oc.insert("AreaInfo.addArea", ai);

		if (returnStr.equals("1")) {// ����ɹ�
			if (paramMap.get("CareReaderTd").toString().trim().length() > 0) {
				List list = Arrays.asList(paramMap.get("CareReaderTd")
						.toString().trim().split(","));

				ad.setAreaname(paramMap.get("areaName").toString().trim());
				ad.setType("��վ");

				for (int i = 0; i < list.size(); i++) {
					ad.setPointid(Integer.parseInt(list.get(i).toString()));

					oc.insert("AreaDetail.addAreaDetail", ad);
				}
			}
			if (paramMap.get("LocatorTd").toString().trim().length() > 0) {
				List list = Arrays.asList(paramMap.get("LocatorTd").toString()
						.trim().split(","));

				ad.setAreaname(paramMap.get("areaName").toString().trim());
				ad.setType("��λ��");

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
			flag = "���������ظ�";
		}

		// ����վ�Ƿ��ظ�
		List careReaderTdList = Arrays.asList(paramMap.get("CareReaderTd")
				.toString().trim().split(","));
		List locatorTdList = Arrays.asList(paramMap.get("LocatorTd").toString()
				.trim().split(","));

		ai.setAreaname(paramMap.get("areaName").toString().trim());
		ai.setAreadetailtype("��վ");
		ai.setPointlist(careReaderTdList);

		List<AreaDetail> CareReaderTdList = oc.selectWithObjectString(
				"AreaDetail.selectAreaDetailByList", ai);

		if (flag.length() > 0) {
			if (CareReaderTdList.size() > 0) {
				flag += "; �����ظ��ķ�վ";
			}
		} else {
			if (CareReaderTdList.size() > 0) {
				flag += "�����ظ��ķ�վ";
			}
		}

		ai.setAreadetailtype("��λ��");
		ai.setPointlist(locatorTdList);

		List<AreaDetail> LocatorTdList = oc.selectWithObjectString(
				"AreaDetail.selectAreaDetailByList", ai);

		if (flag.length() > 0) {
			if (LocatorTdList.size() > 0) {
				flag += "; �����ظ��Ķ�λ��";
			}
		} else {
			if (LocatorTdList.size() > 0) {
				flag += "�����ظ��Ķ�λ��";
			}
		}

		returnMap.put("CheckResult", flag);

		return returnMap;
	}

	public List<AreaInfo> areaInfoOfIndexRight() {
		OperateClass oc = new OperateClass();
		// ������е�����
		List<AreaInfo> areaInfoList = oc
				.selectWithObjectString("AreaInfo.selectAllArea");
		// ��ø������µĻ�վ�Ͷ�λ����
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
