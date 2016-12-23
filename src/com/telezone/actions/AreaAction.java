package com.telezone.actions;

import java.util.HashMap;
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

import com.telezone.domain.classes.AreaInfo;
import com.telezone.services.IArea;
import com.telezone.serviesImpl.AreaImpl;

public class AreaAction extends DispatchAction {
	private final Logger logger = Logger.getLogger(this.getClass());

	private IArea area;

	/**
	 * 方法名含义：添加区域信息
	 * 
	 * @Date 2010 - 07 - 19
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public ActionForward addArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		this.setArea(new AreaImpl());

		String areaName = request.getParameter("areaName") == null ? "0"
				: request.getParameter("areaName");
		String areaType = request.getParameter("areaType") == null ? "0"
				: request.getParameter("areaType");
		String areaMaxPerson = request.getParameter("areaMaxPerson") == null ? "0"
				: request.getParameter("areaMaxPerson");
		String areaOverTime = request.getParameter("areaOverTime") == null ? "0"
				: request.getParameter("areaOverTime");
		String areaCaiJuePerson = request.getParameter("areaCaiJuePerson") == null ? "0"
				: request.getParameter("areaCaiJuePerson");
		String CareReaderTd = request.getParameter("CareReaderTd") == null ? "0"
				: request.getParameter("CareReaderTd");
		String LocatorTd = request.getParameter("LocatorTd") == null ? "0"
				: request.getParameter("LocatorTd");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("areaName", areaName);
		paramMap.put("areaType", areaType);
		paramMap.put("areaMaxPerson", areaMaxPerson);
		paramMap.put("areaOverTime", areaOverTime);
		paramMap.put("areaCaiJuePerson", areaCaiJuePerson);
		paramMap.put("CareReaderTd", CareReaderTd);
		paramMap.put("LocatorTd", LocatorTd);

		Map<String, Object> checkMap = area.checkArea(paramMap);

		JSONObject jsonObj = new JSONObject();

		if (checkMap.get("CheckResult").toString().trim().length() == 0) {
			Map<String, Object> returnMap = area.addArea(paramMap);
			jsonObj.put("data", returnMap.get("returnStr").toString());
			jsonObj.put("flag", "");
		} else {
			jsonObj.put("flag", checkMap.get("CheckResult").toString().trim());
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
	 * 右侧的区域信息定时刷新访问地址
	 * 
	 * @param mapping
	 * @param null
	 * @param request
	 * @param response
	 * @return Json
	 * @throws Exception
	 */
	public ActionForward areaInfoOfIndexRight(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setArea(new AreaImpl());

		List<AreaInfo> returnList = area.areaInfoOfIndexRight();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Data", returnList);

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			logger.info(e);
		}

		return null;
	}

	/**
	 * 点击右侧的区域信息时访问的地址
	 * 
	 * @param mapping
	 * @param 分站ID，定位器ID
	 * @param request
	 * @param response
	 * @return Json
	 * @throws Exception
	 */
	public ActionForward areaDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setArea(new AreaImpl());

		String cardreaderid = request.getParameter("c") == null ? "0" : request
				.getParameter("c");
		String locatorid = request.getParameter("l") == null ? "0" : request
				.getParameter("l");
		String areaName = request.getParameter("n") == null ? "0" : request
				.getParameter("n");

		List data = area.areaDetail(cardreaderid, locatorid, areaName);

		request.setAttribute("Data", data);

		return mapping.findForward("success");
	}

	public IArea getArea() {
		return area;
	}

	public void setArea(IArea area) {
		this.area = area;
	}

}
