package com.telezone.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.telezone.services.IEscapeLine;
import com.telezone.serviesImpl.EscapeLineImpl;

public class EscapeLineAction extends DispatchAction {
	private IEscapeLine escapeLine;

	public void save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setEscapeLine(new EscapeLineImpl());
		
		String name = request.getParameter("n") == null ? "0" : request.getParameter("n");
		String info = request.getParameter("i") == null ? "0" : request.getParameter("i");
		String point = request.getParameter("p") == null ? "0" : request.getParameter("p");
		String mapid = request.getParameter("m") == null ? "0" : request.getParameter("m");
		String isShow = request.getParameter("s") == null ? "0" : request.getParameter("s");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		parameterMap.put("name", name);
		parameterMap.put("info", info);
		parameterMap.put("point", point);
		parameterMap.put("mapid", mapid);
		parameterMap.put("isShow", isShow);
		
		Map<String, Object> returnMap = escapeLine.saveEscapeLine(parameterMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("suc", returnMap.get("suc"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ActionForward getLine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.setEscapeLine(new EscapeLineImpl());
		
		String name = request.getParameter("n") == null ? "0" : request.getParameter("n");
		
		name = new String(name.getBytes("iso8859-1"), "UTF-8");
		
		Map<String, Object> returnMap = escapeLine.getLine(name);
		
		request.setAttribute("list", returnMap.get("list"));
		
		return mapping.findForward("EscapeLine");
	}
	
	public void modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.setEscapeLine(new EscapeLineImpl());
		
		String oldname = request.getParameter("on") == null ? "0" : request.getParameter("on");
		String name = request.getParameter("n") == null ? "0" : request.getParameter("n");
		String info = request.getParameter("i") == null ? "0" : request.getParameter("i");
		String isShow = request.getParameter("s") == null ? "0" : request.getParameter("s");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		parameterMap.put("oldname", oldname);
		parameterMap.put("name", name);
		parameterMap.put("info", info);
		parameterMap.put("isShow", isShow);
		
		Map<String, Object> returnMap = escapeLine.modify(parameterMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("suc", returnMap.get("str"));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getLineByMapid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.setEscapeLine(new EscapeLineImpl());
		
		String mapid = request.getParameter("m") == null ? "0" : request.getParameter("m");
		
		Map<String, Object> returnMap = escapeLine.getLineByMapid(mapid);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", returnMap.get("list"));
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public IEscapeLine getEscapeLine() {
		return escapeLine;
	}

	public void setEscapeLine(IEscapeLine escapeLine) {
		this.escapeLine = escapeLine;
	}
}
