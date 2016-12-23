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
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.bo.HistoryNote_LocatorBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-07-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class HistoryNote_LocatorAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request ��ʷ��¼��ѯ��ķ�վ�Ķ�λ��Ϣ
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        String cid=request.getParameter("cid");      
        String date=request.getParameter("date");    
        HistoryNote_LocatorBO bo=new HistoryNote_LocatorBO();
        try{
        	 List list=bo.init(date,cid);
        	 request.setAttribute("cid",cid);
        	 request.setAttribute("HistoryNote_Locator_List",list);
        }catch(Exception e){
        	log.error("==��ʷ��¼��ѯ==",e);
        }
		return mapping.findForward("show");
	}

}

