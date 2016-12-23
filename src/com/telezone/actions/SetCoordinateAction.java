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

import com.telezone.domain.classes.CardReader;
import com.telezone.services.ISetCoordinate;
import com.telezone.serviesImpl.SetCoordinateImpl;

public class SetCoordinateAction extends DispatchAction {
	private final Logger logger = Logger.getLogger(this.getClass());

	private ISetCoordinate setCoordinate;
	
	/**
	 * 方法名含义：获得所要修改分站的详细信息
	 * 
	 * @Date 2010 - 08 - 15
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public ActionForward getReader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 		this.setSetCoordinate(new SetCoordinateImpl());
		CardReader cardReader=new CardReader();
		String cardid=request.getParameter("id");
		JSONObject jsonObj = new JSONObject();
		List list=setCoordinate.GetCardReader(cardid);
		if(list.size()>0){
			cardReader=(CardReader) list.get(0);
			jsonObj.put("reader", cardReader);

		}
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 方法名含义：给WEB程序设置基站或者定位器坐标
	 * 
	 * @Date 2010 - 08 - 15
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public ActionForward setxy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 		this.setSetCoordinate(new SetCoordinateImpl());
		CardReader cardReader=new CardReader();
		Map returnMap = setCoordinate.GetAllCoordinate();
		String cardid=request.getParameter("cardid");
		List list=setCoordinate.GetCardReader(cardid);
		if(list.size()>0){
			cardReader=(CardReader) list.get(0);
		}
		
		request.setAttribute("cardid", cardid);
		request.setAttribute("cardReader",cardReader );
		request.setAttribute("cr", returnMap.get("cr"));
		request.setAttribute("l", returnMap.get("l"));
		request.setAttribute("ml", returnMap.get("ml"));
		
		return mapping.findForward("success");
	}
	
	/**
	 * 方法名含义：给WEB程序设置基站或者定位器坐标
	 * 
	 * @Date 2010 - 08 - 15
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public void savexy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setSetCoordinate(new SetCoordinateImpl());
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String x = request.getParameter("webx");
		String y = request.getParameter("weby");
		String mapid = request.getParameter("mapid");
		
		String returnStr = setCoordinate.saveXYOfCardreaderOrLocator(type, id, x, y, mapid);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnStr);

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ISetCoordinate getSetCoordinate() {
		return setCoordinate;
	}

	public void setSetCoordinate(ISetCoordinate setCoordinate) {
		this.setCoordinate = setCoordinate;
	}
}
