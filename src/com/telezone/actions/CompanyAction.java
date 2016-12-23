package com.telezone.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.MapList;

public class CompanyAction extends DispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String isCompany = "1";
		
		//判断登录IP是否是集团
		if(request.getSession().getAttribute("isCompany").toString().equals("0")) {
			isCompany = "0";
		}else {
			request.getSession().setAttribute("isCompany", "0");
		}
		
		OperateClass oc = new OperateClass();
		
		MapList ml = new MapList();
		ml.setMaplevel(0);
		
		List<MapList> list = oc.selectWithObjectString("MapList.selectAllDataByParameter", ml);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("isCompany", isCompany);
		jsonObj.put("map", list.get(0));

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
