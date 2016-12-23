//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.Search_WeizhidisLeft;
import com.kj133.entity.bo.WeizhidisListBO;

/**
 * MyEclipse Struts Creation date: 02-28-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class WeizhidisLeftAction extends DispatchAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WeizhidisListBO bo = new WeizhidisListBO();
		DynaActionForm dy = (DynaActionForm) form;
		try {
			
			Search_WeizhidisLeft left = (Search_WeizhidisLeft) dy
					.get("ser_weizhidisLeft");
			List list = bo.init(left);
			if (list.size() == 0) {
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"no.count"));
				this.saveMessages(request, messages);
			} else {
				request.setAttribute("weizhidis_list", list);
			}
			
			dy.set("ser_weizhidisLeft", left);
		} catch (Exception e) {
			log.error("==Œª÷√∑÷≤º==",e);
		}
		return mapping.findForward("weizhidisleft");
	}

}
