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

import com.kj133.entity.EmergencyAdd;
import com.kj133.entity.bo.EmergencyListBO;
import com.kj133.util.Global;
 

/** 
 * MyEclipse Struts
 * Creation date: 03-12-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class EmergencyAddAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	private final Logger  log=Logger.getLogger(this.getClass());
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
        EmergencyListBO bo=new EmergencyListBO();
        Global go=new Global();
        try{
        	EmergencyAdd map=(EmergencyAdd)dy.get("emergencyadd");
        	  String name=map.getName();
              List count=go.juede("select * from ExitPath where pathname= ?",name);
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
        	log.error("==���ӱ���·��==",e);
        }
		return mapping.findForward("add");
	}
  
	 
}

