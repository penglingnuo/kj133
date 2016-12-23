package com.telezone.serviesImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.CardReader;
import com.telezone.domain.classes.Locator;
import com.telezone.domain.classes.MapList;
import com.telezone.services.ISetCoordinate;

public class SetCoordinateImpl implements ISetCoordinate {
	public Map GetAllCoordinate() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		OperateClass oc = new OperateClass();
		
		List allCardreader = oc.selectWithObjectString("CardReader.selectCardReaderByParam", new CardReader());

		List allLocator = oc.selectWithObjectString("Locator.selectLocator", new Locator());
		
		List mapList = oc.selectWithObjectString("MapList.selectAllData");
		
		returnMap.put("cr", allCardreader);
		returnMap.put("l", allLocator);
		returnMap.put("ml", mapList);
		
		return returnMap;
	}
	@Override
	public List GetCardReader(String cardid) {//获取查找的分站
		OperateClass oc = new OperateClass();
		CardReader cardReader=new CardReader();
		cardReader.setCardreaderid(Integer.parseInt(cardid));
		List relist = oc.selectWithObjectString("CardReader.selectCardReaderByCardReaderId", cardReader);
		return relist;
	}
	public String saveXYOfCardreaderOrLocator(String type, String id, String x, String y, String mapid) {
		OperateClass oc = new OperateClass();

		String returnStr = "1";
		
		try {
			if(type.equals("0")) {
				Locator l = new Locator();
				
				l.setLocatorId(Integer.parseInt(id));
				l.setWebx(Integer.parseInt(x));
				l.setWeby(Integer.parseInt(y));
				
				oc.update("Locator.updateLocator", l);
			}else if(type.equals("1")) {
				CardReader cr = new CardReader();
				
				cr.setCardreaderid(Integer.parseInt(id));
				cr.setWebx(x);
				cr.setWeby(y);
				
				oc.update("CardReader.updateCardReader", cr);
			}
		} catch (Exception e) {
			returnStr = "0";
		}
		
		return returnStr;
	}

}