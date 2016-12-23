package com.telezone.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.telezone.services.IIndexRightData;
import com.telezone.serviesImpl.IndexRightDataImpl;

public class GetIndexRightDataAction extends DispatchAction {
	private static final long serialVersionUID = 542714564562726395L;

	private IIndexRightData getMapListData;

	private final Logger log = Logger.getLogger(this.getClass());

	/*
	 * 获得报警信息的数据
	 * 
	 * @returnParam 信息列表 @return json @Date 2010-06-08 @Author LYees
	 */
	public ActionForward alarminfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setGetMapListData(new IndexRightDataImpl());

		// 报警信息String
		Map returnAlarmInfoMap = getMapListData.alarmInfoMap();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("SubStationAlarmInfo", returnAlarmInfoMap.get("SubStationAlarmInfo"));
		jsonObj.put("CardBettyAlarmInfo", returnAlarmInfoMap.get("CardBettyAlarmInfo"));
		jsonObj.put("OverTime", returnAlarmInfoMap.get("OverTime"));
		jsonObj.put("overMaxPerson", returnAlarmInfoMap.get("overMaxPerson"));
		jsonObj.put("overMaxPersonInMine", returnAlarmInfoMap.get("overMaxPersonInMine"));
		jsonObj.put("LocatorAlarm", returnAlarmInfoMap.get("LocatorAlarm"));
		jsonObj.put("stopArea", returnAlarmInfoMap.get("stopArea"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 获得地图列表的数据
	/*
	 * @returnParam 地图列表，报警信息列表 @return index_right.jsp @Date 2010-06-07 @Author
	 * LYee
	 */
	public ActionForward indexright(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setGetMapListData(new IndexRightDataImpl());

		// 地图列表List
		List returnMapList = getMapListData.mapList();

		request.setAttribute("mapList", returnMapList);

		return mapping.findForward("success");
	}
	
	public void maplist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setGetMapListData(new IndexRightDataImpl());

		// 地图列表List
		List returnMapList = getMapListData.mapList();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("maplist", returnMapList);

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IIndexRightData getGetMapListData() {
		return getMapListData;
	}

	public void setGetMapListData(IIndexRightData getMapListData) {
		this.getMapListData = getMapListData;
	}
}
