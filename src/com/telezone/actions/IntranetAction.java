package com.telezone.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.telezone.domain.classes.IntranetInfo;
import com.telezone.services.IIntranet;
import com.telezone.serviesImpl.IntranetImpl;

/**
 * Ò³ÃæÍ³¼Æ Action
 * 
 * @param IntranetInfo
 * 
 * @return Map
 * 
 * @author yeZhen
 * 
 * @date 2011-03
 * 
 */
public class IntranetAction extends DispatchAction {

	private static final long serialVersionUID = 1L;
	private IntranetInfo intranetinfo;
	private IIntranet intranet;

	public void getCountList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String callback = request.getParameter("callback");
		this.setIntranet(new IntranetImpl());
		
		Map<String, Object> returnMap = intranet.getList();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnMap.get("list"));
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(callback  + "(" + jsonObj.toString() + ");");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IntranetInfo getIntranetinfo() {
		return intranetinfo;
	}

	public void setIntranetinfo(IntranetInfo intranetinfo) {
		this.intranetinfo = intranetinfo;
	}

	public IIntranet getIntranet() {
		return intranet;
	}

	public void setIntranet(IIntranet intranet) {
		this.intranet = intranet;
	}
}
