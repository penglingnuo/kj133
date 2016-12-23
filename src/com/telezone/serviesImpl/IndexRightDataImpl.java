package com.telezone.serviesImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.AlarmInfoObject;
import com.telezone.domain.classes.AreaDetail;
import com.telezone.domain.classes.AreaInfo;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.PersonInMine;
import com.telezone.services.IIndexRightData;

public class IndexRightDataImpl implements IIndexRightData {

	private final Logger logger = Logger.getLogger(this.getClass());

//	static {
//		OperateClass oc = new OperateClass();
//		//��÷�վ�ж���Ϣ
//		Connection conn = oc.getCurrentConnection();
//		try {
//			CallableStatement cstmt = conn
//					.prepareCall("{call dbo.Calreaderhalt()}");
//			cstmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	/*
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return ���ص�ͼ�б�List
	 * 
	 */
	public List mapList() {
		OperateClass oc = new OperateClass();
		List list = oc.selectWithObjectString("MapList.selectAllData");
		return list;
	}

	/*
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return ���ر�����ϢList
	 */
	public List alarmInfoList() {
		OperateClass oc = new OperateClass();
		List list = oc.selectWithObjectString("AlarmInfo.selectAlarmInfo");
		return list;
	}

	/*
	 * ���ҳ���Ҳ�ı�����Ϣ
	 * 
	 * �ڶ����޸�ʱ�䣺2010-07-07
	 * 
	 * @Author LYee
	 * 
	 * @Date 2010-06-07
	 * 
	 * @Return ���ر�����ϢMap
	 */
	public Map<String, Object> alarmInfoMap() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		//��վ������Ϣ
		List list = new ArrayList();
		
		CardReader cr = new CardReader();
		cr.setState("����");

		Map<String, Object> subStationParamMap = new HashMap<String, Object>();
		subStationParamMap.put("key", "1");
		subStationParamMap.put("Obj", cr);
		List subStationList = oc.selectCardReaderAndLocatorData(
				"CardReader.selectCardReaderByParam", subStationParamMap);

		for (int i = 0; i < subStationList.size(); i++) {
			CardReader _cr = (CardReader) subStationList.get(i);
			// ��û�վ״̬
			List<AlarmInfoObject> cardReaderState = oc.selectWithObjectString(
					"ReaderState611.selectCardReaderByStateAlarm", _cr);

			// ��վ������
			if(cardReaderState.size() > 0) {
				list.add(cardReaderState.get(0));
			}
		}

		// ��ñ�����Ϣ�����ݣ�Ȼ���������������Ϣ
		returnMap.put("SubStationAlarmInfo", list);

		// ��ÿ���Ϣ
		List CardBetty = new ArrayList();
		CardBetty = oc.selectWithObjectString("CardBetty.selectCardBetty");

		// ��ñ�����Ϣ�����ݣ�Ȼ���������������Ϣ
		returnMap.put("CardBettyAlarmInfo", CardBetty);

		// ������Ա��ʱ
		PersonInMine pim = new PersonInMine();
		pim.setCrname("���³�ʱ");
		List OverTimeCardList = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectOverTimeCard", pim);
		returnMap.put("OverTime", OverTimeCardList);

		List<AreaInfo> areaInfoList = oc
				.selectWithObjectString("AreaInfo.selectAllArea");

		// ����Ա������ʱ��������뱨��һ�鴦��
		List<AreaInfo> overMaxPerson = new ArrayList<AreaInfo>();
		List<AreaInfo> overTime = new ArrayList<AreaInfo>();
		List<AreaInfo> stopArea = new ArrayList<AreaInfo>();

		// ���Ȼ�ñ������µ����п���Ϣ
		for (AreaInfo _ai : areaInfoList) {
			// ��ø������µ�������Ա
			List stafferInAreaList = oc.selectWithObjectString(
					"Area.selectStafferInAreaByAreaId", _ai);

			// �������
			if (_ai.getAreatype().contains("����")) {
				if (stafferInAreaList.size() > 0) {
					_ai.setStafferinarea(stafferInAreaList);
					stopArea.add(_ai);
				}
			} else {
				// ����Ա
				if (Integer.parseInt(_ai.getMaxsum()) < stafferInAreaList
						.size()) {
					_ai.setStafferinarea(stafferInAreaList);
					overMaxPerson.add(_ai);
				}
			}
		}

		returnMap.put("overMaxPerson", overMaxPerson);
		returnMap.put("stopArea", stopArea);

		//���³�Ա
		pim.setCrname("���³�Ա");
		List _list = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectOverNumber", pim);
		
		int maxNumber = ((PersonInMine)_list.get(0)).getCardid().intValue();
		
		List _l = oc.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMine");
		
		if(maxNumber < _l.size()) {
			returnMap.put("overMaxPersonInMine", _l.size() - maxNumber);
		}else {
			returnMap.put("overMaxPersonInMine", "0");
		}
		
		//��λ������
		List locatorAlarmList = oc.selectWithObjectString("Locator.selectAlarmLocator");
		returnMap.put("LocatorAlarm", locatorAlarmList);
		
		return returnMap;
	}
}
