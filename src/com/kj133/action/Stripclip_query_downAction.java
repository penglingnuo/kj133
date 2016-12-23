//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.kj133.entity.bo.Stripclip_query_downBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Stripclip_query_downAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
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

        Stripclip_query_downBO bo=new Stripclip_query_downBO();
        
		try{
		
			List list=bo.check(rt,areaid);
		       request.setAttribute("relist",list);
		}catch(Exception e){
		   e.printStackTrace();
		}
        return mapping.findForward("init");
	}
}

