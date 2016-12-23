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

import com.kj133.entity.bo.Area_overtime_downBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Area_overtime_downAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域超时明细
	 * @param response
	 * @return ActionForward
	 */


	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        String areaid=request.getParameter("areaid");
        request.getSession().setAttribute("areaid",areaid);//用于传递编号
        String stime = request.getParameter("st");
        request.getSession().setAttribute("stime", stime);
        String etime = request.getParameter("et");
        request.getSession().setAttribute("etime", etime);

        Area_overtime_downBO bo=new Area_overtime_downBO();
        
		try{
		
			List list=bo.check(stime,etime,areaid);
			request.setAttribute("listCount1", list.size());
			request.setAttribute("relist",list);
			request.getSession().setAttribute("area_overtime_downPrint", list);//结果集
		       
		}catch(Exception e){
			log.error("==区域超时报警明细==",e);
		}
        return mapping.findForward("init");
	}

}

