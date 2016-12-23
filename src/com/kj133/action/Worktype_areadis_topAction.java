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
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_worktype_areadis_top;
import com.kj133.entity.bo.Worktype_areadis_topBO;

import com.kj133.entity.bo.WordlibBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Worktype_areadis_topAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  工种区域分布表---工种分布
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		Pagination pagination = new Pagination();
        DynaActionForm dy=(DynaActionForm)form;
        String page =(String)request.getParameter("page");//获取页号
        Worktype_areadis_topBO bo=new Worktype_areadis_topBO();
        WordlibBO lib=new WordlibBO();
	    try{
	    	Search_worktype_areadis_top  worktype_areadis_top=(Search_worktype_areadis_top)dy.getMap().get("worktype_areadis_top");
	    	pagination.setCount(200); 
		     if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }

	        	 List list=bo.init(pagination);
	        	
	        	 request.setAttribute("listCount", list.size());

	        	 if( list.size()== 0){
	        		   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);
	        	 }else{
	        		 request.setAttribute("relist",list);
	        		 request.getSession().setAttribute("worktype_areadis_top", worktype_areadis_top);//结果集
	        	 }
	        	 request.setAttribute("pagination", pagination);

	        	 List areaid = lib.areaid_areaname();
			      request.setAttribute("areaid_list",areaid);


	        	 dy.set("worktype_areadis_top",worktype_areadis_top);
	        }catch(Exception e){
	        	log.error("==工种区域分布表---工种分布==",e);
	        }
			return mapping.findForward("show");
	}

}

