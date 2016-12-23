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

import com.kj133.entity.bo.Area_padlock_downBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Area_padlock_downAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域禁入报警查询
	 * @param response
	 * @return ActionForward
	 */


	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		Global go=new Global();
        String atime=request.getParameter("atime");
        String aid=request.getParameter("aid");
        request.getSession().setAttribute("atime",atime);//用于传递编号

         
		String s1=atime.substring(0,10);
		String ss=go.getDay(s1,-1); 
				
		String s2=atime.substring(11,19); 
		
		String atime1=atime.substring(0,19);  
		String btime = ss+" "+s2;


        Area_padlock_downBO bo=new Area_padlock_downBO();
        
		try{
		
			List list=bo.check(aid,btime,atime1);
			request.setAttribute("listCount1", list.size());
		    request.setAttribute("relist",list);
		    request.getSession().setAttribute("area_padlock_downPrint", list);//结果集
		}catch(Exception e){
			log.error("==区域禁入报警查询==",e);
		}
        return mapping.findForward("init");
	}

}

