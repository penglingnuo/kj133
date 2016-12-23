package com.telezone.serviesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.kj133.entity.Ouser;
import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.PersonInMine;
import com.telezone.domain.classes.PersonInMineDetailInfo;
import com.telezone.domain.classes.Staffer;
import com.telezone.services.IPersonInMine;
import com.telezone.util.Common;

public class PersonInMineImpl implements IPersonInMine {
	private final Logger logger = Logger.getLogger(this.getClass());

	public Map<String, Object> getThePlaceOfPersonsInMine() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();
		// û���źŵĿ��б�
		List<PersonInMineDetailInfo> withoutSignalCardList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMineWithoutSignal");
		// �п��źŵ��б�
		List<PersonInMineDetailInfo> withSignalCardList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMineWithSignal");

		// û�п��źŻ�������һ������

		// �������List�����ݽ�ֹ����վ���ƣ���߻���Ҫ����������ݣ����￪ʼ���������ݵı���

		returnMap.put("withoutSignalCardList", withoutSignalCardList);
		returnMap.put("withSignalCardList", withSignalCardList);

		return returnMap;
	}

	public Map<String, Object> getTheNumberOfPersonsInMine() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		// ������й����Ļ�վ
		CardReader cr = new CardReader();
		cr.setState("����");
		cr.setCardreaderid(new Integer(0));
		List<CardReader> workedCardReaderList = oc.selectWithObjectString(
				"CardReader.selectCardReaderByParam", cr);

		// ������в���
		List<PersonInMineDetailInfo> departmentList = oc
				.selectWithObjectString("Staffer.selectStafferDepartment");

		// ���о�����Ա��Ϣ
		List<PersonInMine> personInMineList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMine1");

		List<Integer> _workedCardReaderList = new ArrayList<Integer>();// ��վID����
		List<Object> _workedCardReaderListNumber = new ArrayList<Object>();// ��վ�µĸ����ļ���

		_workedCardReaderListNumber.add("�ϼ�");
		//�ܼƵ�һ��
		_workedCardReaderListNumber.add(0);

		// �ѻ�վID�ŵ���վID������
		for (int i = 0; i < workedCardReaderList.size(); i++) {
			CardReader cr1 = workedCardReaderList.get(i);
			_workedCardReaderList.add(cr1.getCardreaderid());
			//����������Ա����
			_workedCardReaderListNumber.add(0);
		}

		// ���о�����Ա������Ϣ
		List<PersonInMine> allPersonInMineList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectCardInCardReaderCount");

		for (int i = 0; i < allPersonInMineList.size(); i++) {
			PersonInMine pim = allPersonInMineList.get(i);
			Integer in = pim.getCardreaderid();

			int index = _workedCardReaderList.indexOf(in) + 2;
			_workedCardReaderListNumber.set(index,
					(Integer) _workedCardReaderListNumber.get(index)
							+ Integer.parseInt(pim.getNumber()));
		}

		for (int i = 2; i < _workedCardReaderListNumber.size(); i++) {
			_workedCardReaderListNumber.set(1,
					((Integer) _workedCardReaderListNumber.get(1))
							+ ((Integer) _workedCardReaderListNumber.get(i)));

		}

		returnMap.put("workedCardReaderList", workedCardReaderList);
		returnMap.put("personInCardReaderList", _workedCardReaderListNumber);

		// ��ʼ���ܸ������ŵ�����
		// ��վ������������
		List<String> _departmentList = new ArrayList<String>();// �������Ƶļ���
		List<Object> resultList = new ArrayList<Object>();// ���Ž���ļ���

		for (int i = 0; i < personInMineList.size(); i++) {
			PersonInMine pim = personInMineList.get(i);

			if (_departmentList.indexOf(pim.getDepartment().trim()) == -1) {// ��������������
				_departmentList.add(pim.getDepartment());

				Common c = new Common();

				List list = c.createListWithInput(_workedCardReaderList.size());

				// �޸Ķ�ӦΪ������
				int cri = pim.getCardreaderid();
				int index = _workedCardReaderList.indexOf(cri) + 2;

				list.set(index, ((Integer) list.get(index)) + 1);
				resultList.add(list);
			} else {// ����������ݣ���������
				int departmentIndex = _departmentList.indexOf(pim
						.getDepartment());
				List list = (List) resultList.get(departmentIndex);

				int cri = pim.getCardreaderid();
				int index = _workedCardReaderList.indexOf(cri) + 2;

				list.set(index, ((Integer) list.get(index)) + 1);

				resultList.set(departmentIndex, list);
			}
		}

		// ����޸�resultList
		for (int i = 0; i < resultList.size(); i++) {
			List list = (List) resultList.get(i);

			list.set(0, _departmentList.get(i));

			int result = 0;

			for (int i1 = 2; i1 < list.size(); i1++) {
				result = result + ((Integer) list.get(i1));
			}

			list.set(1, result);

			resultList.set(i, list);

		}

		returnMap.put("resultList", resultList);

		return returnMap;
	}

	public Map<String, Object> detailedData(String cardReaderId,
			String department) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		// ���о�����Ա��Ϣ
		PersonInMineDetailInfo pidi = new PersonInMineDetailInfo();
		pidi.setCardreader(cardReaderId);
		if (!department.trim().contains("δ��")) {
			pidi.setStafferdepartment(department);
		}

		List<PersonInMine> personInMineList = oc
				.selectWithObjectString(
						"PersonInMineDetailInfo.selectPersonsInMineByDepartmentAndCardreaderId",
						pidi);

		returnMap.put("list", personInMineList);

		return returnMap;
	}

	public Map<String, Object> locationData() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		List<PersonInMine> personInMineList = oc
				.selectWithObjectString(
						"PersonInMineDetailInfo.selectLocationData",
						new PersonInMine());

		returnMap.put("list", personInMineList);

		return returnMap;
	}
	public Map<String, Object> locationDataByuserid(String userid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		PersonInMine pin = new PersonInMine();
		pin.setUserid(userid);
		OperateClass oc = new OperateClass();

		List<PersonInMine> personInMineList = oc
				.selectWithObjectString(
						"PersonInMineDetailInfo.selectLocationDataByuserid",
						pin);

		returnMap.put("list", personInMineList);

		return returnMap;
	}
	public Map<String, Object> locationDataByuseridandstaffername(String userid,String name) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		PersonInMine pin = new PersonInMine();
		pin.setUserid(userid);
		pin.setStaffername(name);
		OperateClass oc = new OperateClass();

		List<PersonInMine> personInMineList = oc
				.selectWithObjectString(
						"PersonInMineDetailInfo.selectLocationDataByuseridandygname",
						pin);

		returnMap.put("list", personInMineList);

		return returnMap;
	}
	
	public Map<String, Object> getWayOfStaffer(String mapId, String cardID) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		PersonInMine pim = new PersonInMine();
		pim.setCardid(Integer.parseInt(cardID));
		pim.setMapid(mapId);

		// ���ָ����Ա���ƶ���ͼ�ϵ�����
		List<PersonInMine> WayOfStaffer = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectWayOfStaffera", pim);
		List<PersonInMine> resultList = new ArrayList<PersonInMine>();

		for (int i = 0; i < WayOfStaffer.size(); i++) {
			PersonInMine p = (PersonInMine) WayOfStaffer.get(i);

			if (i == 0) {
				resultList.add(p);
			} else {
				PersonInMine _p = resultList.get(resultList.size() - 1);

				if (!_p.getCrx().equals(p.getCrx())
						&& !_p.getCry().equals(p.getCry())) {
					resultList.add(p);
				}

			}

		}

		returnMap.put("list", resultList);
		return returnMap;
	}

	public Map<String, Object> getWayOfStaffer(String cardID) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		PersonInMine pim = new PersonInMine();
		pim.setCardid(Integer.parseInt(cardID));

		// ���ָ����Ա���ƶ���ͼ�ϵ�����
		List<PersonInMine> list = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectWayOfStaffera", pim);

		List<PersonInMine> returnList = new ArrayList<PersonInMine>();

		for (int i = 0; i < list.size(); i++) {
			PersonInMine _pim = (PersonInMine) list.get(i);

			if (i == 0) {
				returnList.add(_pim);
			} else {
				PersonInMine __pim = returnList.get(returnList.size() - 1);

				if (__pim.getCardreaderid().intValue() == _pim
						.getCardreaderid().intValue()) {
					__pim.setOuttime(_pim.getOuttime());

					returnList.set(returnList.size() - 1, __pim);
				} else {
					returnList.add(_pim);
				}
			}
		}

		returnMap.put("list", returnList);
		return returnMap;
	}

	public Map<String, Object> search(Map<String, Object> paramMap) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List returnList = new ArrayList();

		String content = paramMap.get("content").toString().trim();
		String userid=paramMap.get("userid").toString().trim();
		OperateClass oc = new OperateClass();

		Staffer s = new Staffer();
		s.setStafferid(content);
    	s.setCardId(content);
		s.setPinyin(content.toUpperCase());
		s.setName(content);
		s.setUserid(userid);
		
		List<Staffer> stafferList = oc.selectWithObjectString(
				"Staffer.selectStafferBy", s);
		
		if(stafferList.size() > 0) {
			for(int i = 0; i < stafferList.size(); i ++) {
				Staffer _s = (Staffer) stafferList.get(i);
				String cardid = _s.getCardId();
				
				if(cardid == null) {
					continue;
				}
				
				PersonInMine _pim = new PersonInMine();
				_pim.setCardid(Integer.parseInt(cardid));
				
				List tempList = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInfoByCardid", _pim);
				
				if(tempList.size() > 0) {
					PersonInMine pim = (PersonInMine) oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInfoByCardid", _pim).get(0);
					
					//��ý����վ��ʱ��
					List list = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInCardreaderInfoByCardid", _pim);
					
					if(list.size() == 1) {
						PersonInMine inTimePersonInMine = (PersonInMine) list.get(0);
						
						pim.setIntime(inTimePersonInMine.getIntime());
					}else if(list.size() > 1) {
						for(int i1 = 1; i1 < list.size(); i1 ++) {
							PersonInMine pim0 = (PersonInMine) list.get(0);
							
							PersonInMine pimi = (PersonInMine) list.get(i1);
							
							if(pimi.getCardreaderid().intValue() == pim0.getCardreaderid().intValue()) {
							}else {
								pim.setIntime(((PersonInMine)list.get(i1-1)).getIntime());
								break;
							}
						}
					}
					
					returnList.add(pim);
				}
			}
		}
		
		returnMap.put("list", returnList);
		
		return returnMap;
	}

	public Map<String, Object> getOutOfTimeCard() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		PersonInMine pim = new PersonInMine();
		pim.setCrname("���³�ʱ");
		List OverTimeCardList = oc.selectWithObjectString(
				"PersonInMineDetailInfo.selectOverTimeCard", pim);

		List<PersonInMineDetailInfo> returnList = new ArrayList<PersonInMineDetailInfo>();

		for (int i = 0; i < OverTimeCardList.size(); i++) {
			PersonInMine _p = (PersonInMine) OverTimeCardList.get(i);

			PersonInMineDetailInfo pimdi = new PersonInMineDetailInfo();

			pimdi.setCardid(_p.getCardid());

			List<PersonInMineDetailInfo> _l = oc.selectWithObjectString(
					"PersonInMineDetailInfo.selectOverTimeCardDetail", pimdi);

			returnList.add(_l.get(0));
		}

		returnMap.put("list", returnList);

		return returnMap;
	}

	public Map<String, Object> getStafferInCardReader(String id, String type, String cardReaderGround,String userid) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		if(type.equals("1")) {//���ҷ�վ
			CardReader cr = new CardReader();
			cr.setCardreaderid(Integer.parseInt(id));
			cr.setUserid(userid);
			
			List list = oc.selectWithObjectString("CardReader.selectCardReaderByCardReaderId", cr);
			returnMap.put("cr", list);
			
			List<PersonInMineDetailInfo> list1 = new ArrayList<PersonInMineDetailInfo>();
			if(cardReaderGround.equals("1")) {//���Ϸ�վ
				list1 = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInCardreaderByCardReaderIdUp", cr);
			}else if (cardReaderGround.equals("0")) {//���·�վ
				list1 = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInCardreaderByCardReaderId", cr);
			}
			
			returnMap.put("list", list1);
		}else if(type.equals("2")) {//��λ��
			
		}
		
		return returnMap;
	}

	public Map<String, Object> getStafferInfo(String id) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		PersonInMine _pim = new PersonInMine();
		_pim.setCardid(Integer.parseInt(id));
		
		PersonInMine pim = (PersonInMine) oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInfoByCardid", _pim).get(0);
		
		//��ý����վ��ʱ��
		List list = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInCardreaderInfoByCardid", _pim);
		
		if(list.size() == 1) {
			PersonInMine inTimePersonInMine = (PersonInMine) list.get(0);
			
			pim.setIntime(inTimePersonInMine.getIntime());
		}else if(list.size() > 1) {
			for(int i = 1; i < list.size(); i ++) {
				PersonInMine pim0 = (PersonInMine) list.get(0);
				
				PersonInMine pimi = (PersonInMine) list.get(i);
				
				if(pimi.getCardreaderid().intValue() == pim0.getCardreaderid().intValue()) {
				}else {
					pim.setIntime(((PersonInMine)list.get(i-1)).getIntime());
					break;
				}
			}
		}
		
		returnMap.put("info", pim);
		
		return returnMap;
	}

	public Map<String, Object> search() {
		return null;
	}
}