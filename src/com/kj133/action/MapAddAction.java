//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

 

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.kj133.entity.MapAdd;
import com.kj133.entity.bo.MapListBO;
import com.kj133.util.Global;
 

/** 
 * MyEclipse Struts
 * Creation date: 03-12-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MapAddAction extends Action {

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
	
	/**
	 *���ӵ�ͼ
	 *��Ϊ����Ҫ��form��method=post��������һ��������action 
	 * */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
        DynaActionForm dy=(DynaActionForm)form;
        MapListBO bo=new MapListBO();
        Global go=new Global();
        try{
        	 MapAdd map=(MapAdd)dy.get("mapadd");
        	  String id=map.getId();
              List count=go.juede("select * from maplist where mapid= ?",id);
        	  if( count != null && count.size()>0)
        	  {
        		  ActionMessages messages=new ActionMessages();
        		  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("erro.add.mapid"));
        		  saveMessages(request,messages);
        	  }else{ 
        		  bo.saveMap(map);
        		  ActionMessages messages=new ActionMessages();
         		  messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("success.add.cardid"));
         		  saveMessages(request,messages);
        	  }
        }catch(Exception e){
        	log.error("==���ӵ�ͼ==",e);
        }
		return mapping.findForward("add");
	}
  
	 
}

