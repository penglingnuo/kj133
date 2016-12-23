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

import com.kj133.entity.Search_emergencyLeft;
import com.kj133.entity.bo.EmergencyListBO;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class EmergencyLeftAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		EmergencyListBO bo=new EmergencyListBO();
        DynaActionForm dy=(DynaActionForm)form;
        try{
        	 Search_emergencyLeft left=(Search_emergencyLeft)dy.get("ser_emergencyLeft");
        	 List list=bo.init(left);
        	 if(list.size()==0){
        		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
        	 }else{
        		 request.setAttribute("emergency_list",list);
        	 }
             dy.set("ser_emergencyLeft",left); 
        }catch(Exception e){
        	log.error("==²éÑ¯±ÜÔÖÂ·Ïß==",e);
        }
        	return mapping.findForward("emergencyleft");
	}
	/**
	 * É¾³ýµØÍ¼
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    String mname=request.getParameter("name");
		   
		    try {
		    	mname = new String(mname.getBytes("iso8859-1"), "UTF-8");
    		} catch (Exception e) {
    		}
		    
		    EmergencyListBO bo=new EmergencyListBO();
		    String[] name=mname.split("\\,");
		    
		    for(int i=0;i<name.length;i++){
			    try{
	               	 	bo.delete(name[i]);
	                	ActionMessages messages=new ActionMessages();
				    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
				    	this.saveMessages(request,messages);
	                
			    }catch(Exception e){
			    	log.error("==É¾³ý±ÜÔÖÂ·Ïß==",e);
			    }
		    }
		    return mapping.findForward("delete");
		 }
}

