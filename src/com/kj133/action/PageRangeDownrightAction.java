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

import com.kj133.entity.bo.PageRangeDownrightBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class PageRangeDownrightAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域设置--区域范围列表
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		String areaname = request.getParameter("areaname");
		String areaorder = request.getParameter("areaorder");

	    try{
	    	PageRangeDownrightBO bo = new PageRangeDownrightBO();
	    	List list=bo.init(areaname,areaorder);
	    	
	    	request.setAttribute("relist",list);
	        request.setAttribute("listCount", list.size());
	        }catch(Exception e){
	        	log.error("==区域设置--区域范围列表==",e);
	        	 
	        }
			return mapping.findForward("show");
			
	}
	

}

