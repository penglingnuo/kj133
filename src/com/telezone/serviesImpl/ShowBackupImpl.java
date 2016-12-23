package com.telezone.serviesImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.PersonInMine;
import com.telezone.domain.classes.ShowBackup;
import com.telezone.domain.classes.Staffer;
import com.telezone.services.IShowBackup;
import com.telezone.util.Common;

public class ShowBackupImpl implements IShowBackup {
	private Logger logger = Logger.getLogger(this.getClass());

	public Map<String, Object> searchHistoryByCardidDate(String keyWord,
			String date) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		if(date.equals("") || date.length() == 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(new Date());
		}
		
		OperateClass oc = new OperateClass();
		
		ShowBackup sb = new ShowBackup();
		sb.setStarttime(date+" 00:00:00");
		sb.setEndtime(date+" 23:59:59");
		sb.setCardid(keyWord);
		
		List list = oc.selectWithObjectString("ShowBackup.searchHistoryByCardidDate", sb);
		
		returnMap.put("list", list);
		
		return returnMap;
	}

	public Map<String, Object> search(String cardId, String recordid)
			throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
	
		OperateClass oc = new OperateClass();

		Staffer s = new Staffer();
		s.setCardId(cardId);

		List slist = oc.selectWithObjectString(
				"Staffer.selectStafferByParameter", s);
		
		ShowBackup sb = new ShowBackup();
		sb.setRecordid(recordid);
		//用于考勤表(workattendanceex)做分区使用，workattendanceex表中没有recordid字段
		//sb.setCardid(cardId);
		
		List _list = oc.selectWithObjectString("ShowBackup.searchHistoryByCardidDate", sb);
		
		sb = (ShowBackup) _list.get(0);
		sb.setCardid(cardId);
		
		List list = oc.selectWithObjectString(
				"ShowBackup.selectShowBackup", sb);

		List<ShowBackup> returnList = new ArrayList<ShowBackup>();

		for (int i = 0; i < list.size(); i++) {
			ShowBackup _sb = (ShowBackup) list.get(i);

			String cardreaderid = _sb.getCardreaderid();
			String ground = _sb.getGround();
			String endtime = _sb.getEndtime();

			if (i == 0) {// 第一个
				returnList.add(_sb);
			} else {// 第一个之后
				if (returnList.size() == 1) {
					ShowBackup _rsb = (ShowBackup) returnList.get(0);
					if (_rsb.getCardreaderid().equals(cardreaderid) == false
							&& _rsb.getGround().equals(ground)) {
						returnList.remove(0);
						returnList.add(_sb);
					} else {
						ShowBackup _rsb1 = (ShowBackup) returnList
								.get(returnList.size() - 1);

						if (_rsb1.getCardreaderid().equals(cardreaderid)) {
							_rsb1.setEndtime(endtime);
							returnList.set(returnList.size() - 1, _rsb1);
						} else if (!_rsb1.getCardreaderid().equals(
								cardreaderid)) {
							returnList.add(_sb);
						}
					}
				} else {
					ShowBackup _rsb = (ShowBackup) returnList
							.get(returnList.size() - 1);

					if (_rsb.getCardreaderid().equals(cardreaderid)) {
						_rsb.setEndtime(endtime);
						returnList.set(returnList.size() - 1, _rsb);
					} else if (!_rsb.getCardreaderid().equals(cardreaderid)) {
						if (ground.equals("1")
								&& _rsb.getGround().equals("1")
								&& !cardreaderid.equals(_rsb
										.getCardreaderid())) {
							break;
						}
						returnList.add(_sb);
					}
				}

			}

		}

		for (int i = 0; i < returnList.size(); i++) {
			ShowBackup _sb = (ShowBackup) returnList.get(i);

			Common common = new Common();

			_sb.setBetweentime(common.betweenTime(_sb.getStarttime(), _sb
					.getEndtime()));

			returnList.set(i, _sb);
		}

		returnMap.put("returnList", returnList);

		PersonInMine pim = new PersonInMine();
		pim.setCardid(Integer.parseInt(((Staffer) slist.get(0)).getCardId()));
		pim.setStafferid(((Staffer) slist.get(0)).getStafferid());
		pim.setStaffername(((Staffer) slist.get(0)).getName());
		pim.setGroup(((Staffer) slist.get(0)).getGroup());

		sb = new ShowBackup();
		sb.setRecordid(recordid);
		//用于考勤表(workattendanceex)做分区使用，workattendanceex表中没有recordid字段
		//sb.setCardid(cardId);

		List _tempList = oc.selectWithObjectString("ShowBackup.selectStaffer", sb);

		pim.setUptime(((PersonInMine) _tempList.get(0)).getUptime());
		pim.setDowntime(((PersonInMine) _tempList.get(0)).getDowntime());

		returnMap.put("sinfo", pim);

		return returnMap;
	}
}
