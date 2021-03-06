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

import com.kj133.entity.bo.Areatime_query_rightBO;



/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Areatime_query_rightAction extends DispatchAction {

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
        String recordtime=request.getParameter("recordtime");
        request.getSession().setAttribute("areaid",areaid);//用于传递编号
        request.getSession().setAttribute("recordtime",recordtime);//用于传递编号
        String rt = recordtime.substring(0, 19);

		Areatime_query_rightBO bo=new Areatime_query_rightBO();
        
		try{
		
			List list=bo.check(rt,areaid);
		       request.setAttribute("relist",list);
		}catch(Exception e){
			log.error("==区域时段查询==",e);
		}
        return mapping.findForward("init");
	}
}

