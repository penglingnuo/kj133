package com.telezone.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Ouser;
import com.telezone.services.ICommon;
import com.telezone.serviesImpl.CommonImpl;

public class CommonAction extends DispatchAction {
	private final Logger logger = Logger.getLogger(this.getClass());

	private ICommon ic;

	/**
	 * 根据输入内容模糊查询
	 * 
	 * @Date 2010 - 08 - 23
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public void staffer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String keyWord = request.getParameter("c") == null ? "0" : request
				.getParameter("c");

		Ouser user=(Ouser)request.getSession().getAttribute("user");
	    String  userid=user.getUserid();

		this.setIc(new CommonImpl());

		Map<String, Object> returnMap = ic.staffer(keyWord,userid);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("staffer", returnMap.get("staffer"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 手动增加考勤记录，初始化
	 * 
	 * @Date 2010 - 08 - 27
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public ActionForward addWorkattendanceExinit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		this.setIc(new CommonImpl());
		Map<String, Object> returnMap = ic.stafferAndLocator();

		request.setAttribute("cardreader", returnMap.get("cardreaderList"));
		request.setAttribute("locator", returnMap.get("locatorList"));

		return mapping.findForward("addworkattendanceex");
	}

	/**
	 * 手动增加考勤记录
	 * 
	 * @Date 2010 - 08 - 27
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 * @throws Exception
	 */
	public void addWorkattendanceEx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cardid = request.getParameter("c") == null ? "0" : request
				.getParameter("c");
		String staffer = request.getParameter("s") == null ? "0" : request
				.getParameter("s");
		String starttime = request.getParameter("st") == null ? "0" : request
				.getParameter("st");
		String endtime = request.getParameter("et") == null ? "0" : request
				.getParameter("et");
		String downcardreader = request.getParameter("dc") == null ? "0" : request
				.getParameter("dc");
		String downlocator = request.getParameter("dl") == null ? "0" : request
				.getParameter("dl");
		String upcardreader = request.getParameter("uc") == null ? "0" : request
				.getParameter("uc");
		String uplocator = request.getParameter("ul") == null ? "0" : request
				.getParameter("ul");

		this.setIc(new CommonImpl());

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("cardid", cardid);
		parameterMap.put("staffer", staffer);
		parameterMap.put("downtime", starttime);
		parameterMap.put("uptime", endtime);
		parameterMap.put("downcardreader", downcardreader);
		parameterMap.put("downlocator", downlocator);
		parameterMap.put("upcardreader", upcardreader);
		parameterMap.put("uplocator", uplocator);
		
		Map<String, Object> returnMap = ic.addWorkattendanceEx(parameterMap);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("flag", returnMap.get("flag"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ICommon getIc() {
		return ic;
	}

	public void setIc(ICommon ic) {
		this.ic = ic;
	}

}
