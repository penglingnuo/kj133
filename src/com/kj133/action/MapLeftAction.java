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

import com.kj133.entity.Search_MapLeft;
import com.kj133.entity.bo.MapListBO;
import com.kj133.util.Global;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MapLeftAction extends DispatchAction {

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
        MapListBO bo=new MapListBO();
        DynaActionForm dy=(DynaActionForm)form;
        try{
        	 Search_MapLeft left=(Search_MapLeft)dy.get("ser_mapLeft");
        	 List list=bo.init(left);
        	 if(list.size()==0){
        		   ActionMessages messages=new ActionMessages();
			       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
			       this.saveMessages(request,messages);
        	 }else{
        		 request.setAttribute("map_list",list);
        	 }
             dy.set("ser_mapLeft",left); 
        }catch(Exception e){
        	log.error("==地图管理==",e);
        }
        	return mapping.findForward("mapleft");
	}
	/**
	 * 删除地图
	 * */
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    String mid=request.getParameter("id");
		    Global go=new Global();
		    MapListBO bo=new MapListBO();
		    String[] name=mid.split("\\,");
		    
		    for(int i=0;i<name.length;i++){
			    try{
	                List count=go.juede(" select * from cardreader where mapid= ?",name[i]);
	                if( count != null && count.size()>0){
	                	ActionMessages messages=new ActionMessages();
				    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.delete.mapid"));
				    	this.saveMessages(request,messages);
	                }else{
	                	 bo.delete(name[i]);
	                	 ActionMessages messages=new ActionMessages();
					    	messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.delete.cardid"));
					    	this.saveMessages(request,messages);
	                	 
	                }
			    }catch(Exception e){
			    	log.error("==删除地图管理==",e);
			    }
		    }
		    return mapping.findForward("delete");
		 }
}

