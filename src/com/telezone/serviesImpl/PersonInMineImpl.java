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
		// 没有信号的卡列表
		List<PersonInMineDetailInfo> withoutSignalCardList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMineWithoutSignal");
		// 有卡信号的列表
		List<PersonInMineDetailInfo> withSignalCardList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMineWithSignal");

		// 没有卡信号获得最近的一次数据

		// 获得数据List，内容截止到基站名称，后边还需要获得其他数据，这里开始做其他数据的遍历

		returnMap.put("withoutSignalCardList", withoutSignalCardList);
		returnMap.put("withSignalCardList", withSignalCardList);

		return returnMap;
	}

	public Map<String, Object> getTheNumberOfPersonsInMine() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		OperateClass oc = new OperateClass();

		// 获得所有工作的基站
		CardReader cr = new CardReader();
		cr.setState("正常");
		cr.setCardreaderid(new Integer(0));
		List<CardReader> workedCardReaderList = oc.selectWithObjectString(
				"CardReader.selectCardReaderByParam", cr);

		// 获得所有部门
		List<PersonInMineDetailInfo> departmentList = oc
				.selectWithObjectString("Staffer.selectStafferDepartment");

		// 所有井下人员信息
		List<PersonInMine> personInMineList = oc
				.selectWithObjectString("PersonInMineDetailInfo.selectPersonInMine1");

		List<Integer> _workedCardReaderList = new ArrayList<Integer>();// 基站ID集合
		List<Object> _workedCardReaderListNumber = new ArrayList<Object>();// 基站下的个数的集合

		_workedCardReaderListNumber.add("合计");
		//总计第一个
		_workedCardReaderListNumber.add(0);

		// 把基站ID放到基站ID集合中
		for (int i = 0; i < workedCardReaderList.size(); i++) {
			CardReader cr1 = workedCardReaderList.get(i);
			_workedCardReaderList.add(cr1.getCardreaderid());
			//各个部门人员数量
			_workedCardReaderListNumber.add(0);
		}

		// 所有井下人员汇总信息
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

		// 开始汇总各个部门的人数
		// 基站汇总数据清零
		List<String> _departmentList = new ArrayList<String>();// 部门名称的集合
		List<Object> resultList = new ArrayList<Object>();// 部门结果的集合

		for (int i = 0; i < personInMineList.size(); i++) {
			PersonInMine pim = personInMineList.get(i);

			if (_departmentList.indexOf(pim.getDepartment().trim()) == -1) {// 如果不存在则加入
				_departmentList.add(pim.getDepartment());

				Common c = new Common();

				List list = c.createListWithInput(_workedCardReaderList.size());

				// 修改对应为的数据
				int cri = pim.getCardreaderid();
				int index = _workedCardReaderList.indexOf(cri) + 2;

				list.set(index, ((Integer) list.get(index)) + 1);
				resultList.add(list);
			} else {// 如果存在数据，则处理数据
				int departmentIndex = _departmentList.indexOf(pim
						.getDepartment());
				List list = (List) resultList.get(departmentIndex);

				int cri = pim.getCardreaderid();
				int index = _workedCardReaderList.indexOf(cri) + 2;

				list.set(index, ((Integer) list.get(index)) + 1);

				resultList.set(departmentIndex, list);
			}
		}

		// 最后修改resultList
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

		// 所有井下人员信息
		PersonInMineDetailInfo pidi = new PersonInMineDetailInfo();
		pidi.setCardreader(cardReaderId);
		if (!department.trim().contains("未绑定")) {
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

		// 获得指定人员在制定地图上的数据
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

		// 获得指定人员在制定地图上的数据
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
					
					//获得进入分站的时间
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
		pim.setCrname("井下超时");
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
		
		if(type.equals("1")) {//查找分站
			CardReader cr = new CardReader();
			cr.setCardreaderid(Integer.parseInt(id));
			cr.setUserid(userid);
			
			List list = oc.selectWithObjectString("CardReader.selectCardReaderByCardReaderId", cr);
			returnMap.put("cr", list);
			
			List<PersonInMineDetailInfo> list1 = new ArrayList<PersonInMineDetailInfo>();
			if(cardReaderGround.equals("1")) {//井上分站
				list1 = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInCardreaderByCardReaderIdUp", cr);
			}else if (cardReaderGround.equals("0")) {//井下分站
				list1 = oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInCardreaderByCardReaderId", cr);
			}
			
			returnMap.put("list", list1);
		}else if(type.equals("2")) {//定位器
			
		}
		
		return returnMap;
	}

	public Map<String, Object> getStafferInfo(String id) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		OperateClass oc = new OperateClass();
		
		PersonInMine _pim = new PersonInMine();
		_pim.setCardid(Integer.parseInt(id));
		
		PersonInMine pim = (PersonInMine) oc.selectWithObjectString("PersonInMineDetailInfo.selectStafferInfoByCardid", _pim).get(0);
		
		//获得进入分站的时间
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