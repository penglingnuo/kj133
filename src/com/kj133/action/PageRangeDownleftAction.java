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

import com.kj133.entity.Search_PageRangeTopleft;
import com.kj133.entity.bo.PageRangeDownleftBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-03-2007
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class PageRangeDownleftAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  区域设置
	 * @param response
	 * @return ActionForward
	 */
	
	/**
	 * 初始化
	 * */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		DynaActionForm dy=(DynaActionForm) form;
		Search_PageRangeTopleft prt=new Search_PageRangeTopleft();
		
	    String areaname=request.getParameter("areaname");
	    String parentarea=request.getParameter("parentarea");
	    String areaorder=request.getParameter("areaorder");
	    String areatype=request.getParameter("areatype");
	    String maxsum=request.getParameter("maxsum");
	    String caijuesum=request.getParameter("caijuesum");
	    String stayminute=request.getParameter("stayminute");
	    String areainfo=request.getParameter("areainfo");
	    
	    prt.setAreaname(areaname);
	    prt.setParentarea(parentarea);
	    prt.setAreaorder(areaorder);
	    prt.setAreatype(areatype);
	    prt.setMaxsum(Integer.parseInt(maxsum));
	    prt.setCaijuesum(Integer.parseInt(caijuesum));
	    prt.setStayminute(Integer.parseInt(stayminute));
	    prt.setAreainfo(areainfo);
	    
	    request.setAttribute("areaname", areaname);
	    request.getSession().setAttribute("areaname", areaname);
	    

	    
	    dy.set("ser_pagerangetopleft",prt);

				
       return mapping.findForward("init");
	}
	
    /**
	    * 得到所属区域
	    * */
		public ActionForward getWordlib(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
			    WordlibBO lib=new WordlibBO();
			    DynaActionForm dy=(DynaActionForm) form;
				try{
					Search_PageRangeTopleft stick=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
				    
				     List name = lib.areaname1();
				     
				     request.setAttribute("name_list",name);
				     dy.set("ser_pagerangetopleft",stick);
					}catch(Exception e){
						log.error("==区域设置--得到所属区域==",e);
					}
				  return mapping.findForward("wordlib");
				 }
		/**
		 * 增加二级区域
		 * */
		public ActionForward add(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
				DynaActionForm dy=(DynaActionForm)form;
				Global go=new Global();
				WordlibBO lib=new WordlibBO();
				
				PageRangeDownleftBO bo=new PageRangeDownleftBO();
				Search_PageRangeTopleft account=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
				try{
					 List count=go.juede(" select areaname from areainfo where areaname  = ? ",account.getAreaname());
					 if(count !=null && count.size()>0){
						 ActionMessages messages = new ActionMessages();
		    		     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.add.areaname"));
		    		     saveMessages(request, messages);
					 }else{
						 
						 List areaid = lib.areaid();
						 int id = areaid.size();
						 
						 bo.add(account,id);
						 ActionMessages messages = new ActionMessages();
						 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.add.cardid"));
						 saveMessages(request, messages);
					 
					 }
					    
				  }catch(Exception e){
					  log.error("==区域设置--增加二级区域==",e);
				  } 
				return mapping.findForward("add");
			}
		
		
		
	    /**
	     * 更新二级区域
	     * */
		public ActionForward update(
				ActionMapping mapping,
				ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response) {
				DynaActionForm dy=(DynaActionForm)form;
				PageRangeDownleftBO bo=new PageRangeDownleftBO();
				Global go=new Global();
				Search_PageRangeTopleft prt=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
				String name = request.getSession().getAttribute("areaname").toString();
				
				String name1=prt.getAreaname();
				request.getSession().setAttribute("areaname", name1);
				String a1=prt.getParentarea();
				String a2 = request.getSession().getAttribute("parentarea").toString();
				
		         try{
		        	 if(a1!=a2){
		        		go.delete(" delete AreaDetail where AreaName=? ", name); 
		        		
		        	 }else{
		        		 
		        	 }
		        	 bo.update(prt,name);
		        	 ActionMessages messages = new ActionMessages();
		        	 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.cardid"));
		        	 saveMessages(request, messages);
		         }catch(Exception e){
		        	 log.error("==区域设置--更新二级区域==",e);
		         }
				return mapping.findForward("update");
			}	
	/**
	 * 删除一级区域  
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dy=(DynaActionForm)form;
		Global go=new Global();
		Search_PageRangeTopleft prt=(Search_PageRangeTopleft)dy.get("ser_pagerangetopleft");
		PageRangeDownleftBO bo=new PageRangeDownleftBO();
		String areaname1 = request.getParameter("areaname1");
		String a2 = request.getParameter("parentarea");

		String a1=prt.getParentarea();
		    try{
	        	 if(a1!=a2){
		        	 ActionMessages messages = new ActionMessages();
		        	 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("true.quyu"));
		        	 saveMessages(request, messages);
	        	 }else{
	        	 go.delete(" delete AreaDetail where AreaName=? ", areaname1);
	        	 bo.delete(areaname1);
	        	 ActionMessages messages = new ActionMessages();
	        	 messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.delete.cardid"));
	        	 saveMessages(request, messages);
	        	 }

		    
		    }catch(Exception e){
		    	log.error("==区域设置--删除一级区域==",e);
		    }
			return mapping.findForward("delete");
	}
	

}

