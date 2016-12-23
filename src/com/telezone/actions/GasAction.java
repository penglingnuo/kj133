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

import com.telezone.domain.classes.GasData;
import com.telezone.services.IGas;
import com.telezone.serviesImpl.GasImpl;

public class GasAction extends DispatchAction {
	private IGas gas;

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 方法名含义：瓦斯报警信息，页面右侧汇总
	 * 
	 * @Date 2010 - 07 - 06
	 * 
	 * @author LYee
	 * 
	 * @return ActionForward
	 * 
	 */
	public ActionForward gasAlarmInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.setGas(new GasImpl());

		List<GasData> returnList = gas.getAlarmInfoOfGas();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("data", returnList);

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public IGas getGas() {
		return gas;
	}

	public void setGas(IGas gas) {
		this.gas = gas;
	}
}
