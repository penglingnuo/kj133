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

import com.kj133.entity.Routing;
import com.kj133.entity.Search_routDetail_top;
import com.kj133.entity.bo.RoutDetail_topBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 05-17-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class RoutDetail_topAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request  巡检路线设置   
	 * @param response
	 * @return ActionForward
	 */
	
	/**
	 * top_show
	 * */
	public ActionForward init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		RoutDetail_topBO bo=new RoutDetail_topBO();
        try{
        	 List list=bo.top_show();
             request.setAttribute("top_show",list);
        }catch(Exception e){
        	log.error("==巡检路线设置==",e);
        }
		return mapping.findForward("routDetail_top");
	}
  
	/**
	 * top_mainFrame_save
	 * */
	public ActionForward save(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		RoutDetail_topBO bo=new RoutDetail_topBO();
		DynaActionForm dy=(DynaActionForm)form;
		Global go=new Global();
        try{
        	Search_routDetail_top top=(Search_routDetail_top)dy.get("routDetail_top");
        	List count=go.juede(" select * from routing where  code= ? ",top.getCode());
        	 if( count != null && count.size()> 0){
				  ActionMessages messages=new ActionMessages();
				  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.add.code"));
				  saveMessages(request, messages);
			  }else{
				 bo.save(top); 
				 ActionMessages messages=new ActionMessages();
				  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.add.locator"));
				  saveMessages(request, messages); 
			  }
        }catch(Exception e){
        	log.error("==巡检路线设置==",e);
        }
		return mapping.findForward("save");
	}
	
	/**
	 * top_mainFrame_load
	 * */
	public ActionForward load(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		RoutDetail_topBO bo=new RoutDetail_topBO();
		DynaActionForm dy=(DynaActionForm)form;
		Routing rg=null;
		String code=request.getParameter("code");
        try{
        	Search_routDetail_top top=(Search_routDetail_top)dy.get("routDetail_top");
            rg=(Routing)bo.load(Routing.class,code);
            top.setCode(rg.getCode());
            top.setRoutename(rg.getRoutename());
            top.setRouteinfo(rg.getRouteinfo());
        }catch(Exception e){
        	log.error("==巡检路线设置==",e);
        }
		return mapping.findForward("load");
	}
	
	/**
	 *  top_mianFrame_update
	 * */
	public ActionForward update(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		RoutDetail_topBO bo=new RoutDetail_topBO();
		DynaActionForm dy=(DynaActionForm)form;
        try{
        	Search_routDetail_top top=(Search_routDetail_top)dy.get("routDetail_top");
        	bo.update(top);
        }catch(Exception e){
        	log.error("==巡检路线设置==",e);
        }
		return mapping.findForward("update");
	}
	
	/**
	 * top_mainFrame_delete
	 * */
	public ActionForward delete(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		RoutDetail_topBO bo=new RoutDetail_topBO();
		String code=request.getParameter("code");
        String[] name=code.split("\\,");
		  for(int i=0;i<name.length;i++){
			    try{
			       bo.delete(name[i]);
			       bo.delete11(name[i]);
			       ActionMessages messages=new ActionMessages();
			    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
			    	this.saveMessages(request,messages);
			    }catch(Exception e){
			    	log.error("==巡检路线设置==",e);
			    }
		   }	 
		  return mapping.findForward("delete");
       } 
}

