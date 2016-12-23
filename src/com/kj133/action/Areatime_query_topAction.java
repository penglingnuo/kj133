//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.kj133.entity.Search_areatime_query_top;
import com.kj133.entity.bo.Areatime_query_topBO;

import com.kj133.entity.bo.WordlibBO;

import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class Areatime_query_topAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域时段查询
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
        Areatime_query_topBO bo=new Areatime_query_topBO();
        WordlibBO lib=new WordlibBO();
	    try{
	    	Search_areatime_query_top  areatime_query_top=(Search_areatime_query_top)dy.getMap().get("areatime_query_top");
	    	pagination.setCount(200); 
		     if (page != null && page.length() > 0) {
		        pagination.setPage(Integer.parseInt(page));
		      }else {
		        pagination.setPage(1);
		      }
		     List list=bo.init(areatime_query_top,pagination);
		     request.setAttribute("listCount", list.size());
		     
		     if( list.size()== 0){
		    	 ActionMessages messages=new ActionMessages();
		    	 messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
		    	 this.saveMessages(request,messages);
		     }else{
		    	 request.setAttribute("relist",list);
		    	 request.getSession().setAttribute("areatime_query_top", areatime_query_top);//结果集
		     }
		     request.setAttribute("pagination", pagination);
		     
		     List areaid = lib.areaid_areaname();
		     request.setAttribute("areaid_list",areaid);
		     
		     
		     dy.set("areatime_query_top",areatime_query_top);

	        }catch(Exception e){
	        	log.error("==区域时段查询==",e);
	        }
			return mapping.findForward("show");
	}
	public ActionForward initPrint(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		Areatime_query_topBO bo=new Areatime_query_topBO();
		try{
			
			Search_areatime_query_top top =(Search_areatime_query_top)request.getSession().getAttribute("areatime_query_top");
			
	        	 List listPrint=bo.initPrint(top);
			
	        	request.setAttribute("areatime_query_topPrint", listPrint);//结果集
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("initPrint");
	}
	
	
	public ActionForward getWordlib(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			Global go=new Global();
	        DynaActionForm dy=(DynaActionForm)form;
		    WordlibBO lib=new WordlibBO();
			  try{
				  Search_areatime_query_top areatime_query_top=(Search_areatime_query_top)dy.getMap().get("areatime_query_top");
				   Calendar cal=Calendar.getInstance();//获取当前时间
				   SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				   String etime=format.format(cal.getTime());
			 
			       String stime=go.getDay(etime,-7);   
			       areatime_query_top.setStime(stime+" 15:47:51");
			       areatime_query_top.setEtime(etime+" 15:47:51");
			       List areaid=lib.areaid_areaname();
			      request.setAttribute("areaid_list",areaid);

				  
			      request.getSession().setAttribute("listCount1", "0");
				   request.setAttribute("listCount", "0");
				   dy.set("areatime_query_top",areatime_query_top);
				}catch(Exception e){
					log.error("==区域时段查询初始化==",e);
				}
			    return mapping.findForward("show");
			 }


}

