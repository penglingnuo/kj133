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

import com.telezone.domain.classes.MapList;
import com.telezone.services.IMapListChange;
import com.telezone.serviesImpl.MapListChangeImpl;

@SuppressWarnings("unchecked")
public class MapListChangeAction extends DispatchAction {

	private static final long serialVersionUID = 542714564562726395L;

	private IMapListChange mapListChange;

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 方法名含义：getCardReaderAndLocatorData，单词首字母
	 * 
	 * @Date 2010 - 06 - 10
	 * 
	 * @author LYee
	 * 
	 * @return null
	 * 
	 */
	public ActionForward getcald(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String mapId = request.getParameter("id") == null ? "1" : request
					.getParameter("id");
			String userid = request.getSession().getAttribute("user1").toString();
			
			this.setGetMapListData(new MapListChangeImpl());

			Map<String, Object> returnMap = mapListChange
					.getCardReaderAndLocatorData(mapId,userid);

			List cardReaderList = (List) returnMap.get("CardReader");
			List LocatorData = (List) returnMap.get("LocatorData");
			String AllStaffers = returnMap.get("AllStaffers").toString();
			MapList ml = (MapList) returnMap.get("MapList");

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("crl", cardReaderList);
			jsonObj.put("ld", LocatorData);
			jsonObj.put("as", AllStaffers);
			jsonObj.put("ml", ml);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			logger.error("==地图信息==",e);
		}

		return null;
	}

	public IMapListChange getGetMapListData() {
		return mapListChange;
	}

	public void setGetMapListData(IMapListChange getMapListData) {
		this.mapListChange = getMapListData;
	}
}
